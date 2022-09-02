package fileio;

import enums.Fields;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import utils.Utils;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class InputLoader {

    private final String inputPath;

    public InputLoader(final String inputPath) {
        this.inputPath = inputPath;
    }

    /**
     * reads the data from .json input tests
     * @return the input
     */
    public Input readData() {
        JSONParser jsonParser = new JSONParser();
        int numberOfYears = 0;
        double santaBudget = 0.0;
        List<ChildInputData> children = new ArrayList<>();
        List<GiftInputData> santaGiftsList = new ArrayList<>();
        List<AnnualChangesInputData> annualChanges = new ArrayList<>();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser
                    .parse(new FileReader(inputPath));
            numberOfYears = Integer.parseInt(String.valueOf(jsonObject.
                    get(Fields.NUMBER_OF_YEARS)));
            santaBudget = Double.parseDouble(String.valueOf(jsonObject.
                    get(Fields.SANTA_BUDGET)));
            JSONObject jsonInitialData = (JSONObject)
                    jsonObject.get(Fields.INITIAL_DATA);
            JSONArray jsonChildren = (JSONArray)
                    jsonInitialData.get(Fields.CHILDREN);
            JSONArray jsonSantaGiftsList = (JSONArray)
                    jsonInitialData.get(Fields.SANTA_GIFTS_LIST);
            JSONArray jsonAnnualChanges = (JSONArray)
                    jsonObject.get(Fields.ANNUAL_CHANGES);

            if (jsonChildren != null) {
                children = Utils.convertChildJson(jsonChildren);
            } else {
                System.out.println("NO CHILDREN");
            }

            if (jsonSantaGiftsList != null) {
                for (Object jsonSantaGift : jsonSantaGiftsList) {
                    santaGiftsList.add(new GiftInputData(
                            (String) ((JSONObject) jsonSantaGift).get(Fields.PRODUCT_NAME),
                            (double) (long) ((JSONObject) jsonSantaGift).get(Fields.PRICE),
                            Utils.stringToCategory((String) ((JSONObject) jsonSantaGift).
                                    get(Fields.CATEGORY)),
                            (int) (long) ((JSONObject) jsonSantaGift).get(Fields.QUANTITY)
                    ));
                }
            } else {
                System.out.println("NO GIFTS");
            }

            if (jsonAnnualChanges != null) {
                for (Object jsonAnnualChange : jsonAnnualChanges) {
                    annualChanges.add(new AnnualChangesInputData(
                            (double) (long) ((JSONObject) jsonAnnualChange).
                                    get(Fields.NEW_SANTA_BUDGET),
                            Utils.convertNewGifts((JSONArray) ((JSONObject) jsonAnnualChange)
                                    .get(Fields.NEW_GIFTS)),
                            Utils.convertChildJson((JSONArray) ((JSONObject) jsonAnnualChange)
                                    .get(Fields.NEW_CHILDREN)),
                            Utils.convertChildrenUpdates((JSONArray)
                                    ((JSONObject) jsonAnnualChange)
                                            .get(Fields.CHILDREN_UPDATES)),
                            Utils.stringToStrategy((String) ((JSONObject) jsonAnnualChange)
                                    .get(Fields.STRATEGY))
                    ));
                }
            } else {
                System.out.println("NO ANNUAL CHANGES");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Input(numberOfYears, santaBudget, children, santaGiftsList, annualChanges);
    }
}
