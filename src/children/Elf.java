package children;

import common.Constants;
import database.Database;
import enums.ElvesType;
import gifts.Gift;

import java.util.Comparator;
import java.util.List;

public final class Elf {
    private Elf() { }

    /**
     * checks what children have the black or pink elf and applies specific changes
     * @param children santa's children list
     */
    public static void checkBlackPinkElf(final List<Child> children) {
        Double budgetUnit = Database.getDatabase().getSantaBudget() / averageScoreSum(children);

        for (Child child : children) {
            double assignedBudget = budgetUnit * child.getAverageScore();
            if (child.getElf() == ElvesType.PINK) {
                assignedBudget += assignedBudget * Constants.PINK_ELF / Constants.PERCENTAGE;
            } else if (child.getElf() == ElvesType.BLACK) {
                assignedBudget -= assignedBudget * Constants.BLACK_ELF / Constants.PERCENTAGE;
            }
            child.setAssignedBudget(assignedBudget);
        }
    }

    /**
     * sum of all children' average score
     * @param children santa's children list
     */
    public static Double averageScoreSum(final List<Child> children) {
        Double sum = 0.0;

        for (Child child : children) {
            sum += child.getAverageScore();
        }

        return sum;
    }

    /**
     * checks what children have the yellow elf and applies specific changes
     * @param children santa's children list
     */
    public static void checkYellowElf(final List<Child> children) {
        List<Gift> santaGifts = Database.getDatabase().getSantaGiftsList();
        santaGifts.sort(Comparator.comparingDouble(Gift::getPrice));

        for (Child child : children) {
            if (child.getReceivedGifts().size() == 0 && child.getElf() == ElvesType.YELLOW) {
                for (Gift gift : santaGifts) {
                    if (child.getGiftPreferences().get(0) != gift.getCategory()) {
                        continue;
                    }
                    if (gift.getQuantity() > 0) {
                        child.getReceivedGifts().add(gift);
                        gift.setQuantity(gift.getQuantity() - 1);
                    }
                    break;
                }
            }
        }
    }
}
