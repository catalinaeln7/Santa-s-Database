package gifts;

import children.Child;
import database.Database;
import enums.Category;

import java.util.Comparator;
import java.util.List;

public final class ShareGifts {
    private ShareGifts() { }

    /**
     * gives gifts to children
     * @param children santa's list of children
     */
    public static void giveGiftToChildren(final List<Child> children) {
        List<Gift> santaGiftsList = Database.getDatabase().getSantaGiftsList();
        santaGiftsList.sort(Comparator.comparingDouble(Gift::getPrice));

        for (Child child : children) {
            Double assignedBudget = child.getAssignedBudget();
            for (Category category : child.getGiftPreferences()) {
                Gift givenGift = null;

                for (Gift gift : santaGiftsList) {
                    if (gift.getCategory() == category && gift.getQuantity() != 0) {
                        givenGift = gift;
                        break;
                    }
                }

                if (givenGift != null && givenGift.getPrice() <= assignedBudget) {
                    givenGift.setQuantity(givenGift.getQuantity() - 1);
                    child.getReceivedGifts().add(givenGift);
                    assignedBudget -= givenGift.getPrice();
                }
            }
        }
    }
}
