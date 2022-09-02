package fileio;

import children.Child;
import database.Database;
import enums.Category;
import enums.Fields;
import gifts.Gift;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Writer {
    private final FileWriter file;
    private static JSONArray annualChildren = new JSONArray();

    public Writer(final String path) throws IOException {
        this.file = new FileWriter(path);
    }

    /**
     * creates and returns the final output json object
     */
    public JSONObject writeFile() {
        JSONObject object = new JSONObject();
        object.put(Fields.ANNUAL_CHILDREN, annualChildren);

        return object;
    }

    /**
     * adds the children array to the final object
     */
    public static void writeAnnualChildren() {
        JSONObject children = new JSONObject();
        children.put(Fields.CHILDREN, returnChildren());
        annualChildren.add(children);
    }

    /**
     * returns an array containing all children
     */
    public static JSONArray returnChildren() {
        JSONArray children = new JSONArray();
        List<Child> dbChildren = Database.getDatabase().getChildren();
        dbChildren.sort(Comparator.comparingInt(Child::getId));

        for (Child child : dbChildren) {
            JSONObject myChild = new JSONObject();
            myChild.put(Fields.ID, child.getId());
            myChild.put(Fields.LAST_NAME, child.getLastName());
            myChild.put(Fields.FIRST_NAME, child.getFirstName());
            myChild.put(Fields.CITY, child.getCity().getValue());
            myChild.put(Fields.AGE, child.getAge());
            myChild.put(Fields.GIFTS_PREFERENCES, returnGiftPreferences(child));
            myChild.put(Fields.AVERAGE_SCORE, child.getAverageScore());
            myChild.put(Fields.NICE_SCORE_HISTORY, new ArrayList<>(child.getNiceScoreHistory()));
            myChild.put(Fields.ASSIGNED_BUDGET, child.getAssignedBudget());
            myChild.put(Fields.RECEIVED_GIFTS, returnReceivedGifts(child));

            children.add(myChild);
        }

        return children;
    }

    /**
     * returns an array containing all child's gift preferences
     * @param child is used to get its gift preferences
     */
    public static JSONArray returnGiftPreferences(final Child child) {
        JSONArray giftPreferences = new JSONArray();

        for (Category category : child.getGiftPreferences()) {
            giftPreferences.add(category.getValue());
        }

        return giftPreferences;
    }

    /**
     * returns an array containing all child's received gifts
     * @param child is used to get its received gifts
     */
    public static JSONArray returnReceivedGifts(final Child child) {
        JSONArray receivedGifts = new JSONArray();

        for (Gift receivedGift : child.getReceivedGifts()) {
            JSONObject gift = new JSONObject();
            if (receivedGift.getCategory() != null) {
                gift.put(Fields.PRODUCT_NAME, receivedGift.getProductName());
                gift.put(Fields.PRICE, receivedGift.getPrice());
                gift.put(Fields.CATEGORY, receivedGift.getCategory().getValue());
            }

            receivedGifts.add(gift);
        }

        return receivedGifts;
    }

    public static JSONArray getAnnualChildren() {
        return annualChildren;
    }

    /**
     * writes the final object in the file and clears all data
     * @param object to be written in json file
     */
    public void closeJSON(final JSONObject object) {
        try {
            file.write(object.toJSONString());
            file.flush();
            file.close();
            annualChildren.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
