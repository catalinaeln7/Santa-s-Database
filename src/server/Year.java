package server;

import averagescore.AverageScoreStrategyFactory;
import children.Child;
import children.Elf;
import database.AnnualChanges;
import database.Database;
import enums.AgeCategory;
import fileio.Writer;
import gifts.GiftsStrategyFactory;
import gifts.IdGiftsStrategy;

import java.util.List;
import java.util.Objects;

public final class Year {
    private Year() { }
    /**
     * the logic for the actions of a year
     * @param flowYear -1 -> initial year; else -> index of annual change
     */
    public static void yearFlow(final int flowYear) {
        List<Child> children = Database.getDatabase().getChildren();

        calculateAgeCategory(children);
        calculateAverageScore(children);

        Elf.checkBlackPinkElf(children);

        chooseStrategyByYear(flowYear);

        Elf.checkYellowElf(children);

        Writer.writeAnnualChildren();

        for (Child child : children) {
            child.grow();
        }
    }

    /**
     * calculates age category for all children: baby, kid, teen, young adult
     * @param children to be calculated for
     */
    public static void calculateAgeCategory(final List<Child> children) {
        for (Child child : children) {
            if (child.getAge() < AgeCategory.KID_AGE) {
                child.setAgeCategory(AgeCategory.BABY);
            } else if (child.getAge() < AgeCategory.TEEN_AGE) {
                child.setAgeCategory(AgeCategory.KID);
            } else if (child.getAge() < AgeCategory.YOUNG_ADULT_AGE) {
                child.setAgeCategory(AgeCategory.TEEN);
            } else {
                child.setAgeCategory(AgeCategory.YOUNG_ADULT);
            }
        }
        children.removeIf(child -> Objects.equals(child.getAgeCategory(),
                AgeCategory.YOUNG_ADULT));
    }

    /**
     * calculates the average score for all children
     * @param children to be calculated for
     */
    public static void calculateAverageScore(final List<Child> children) {
        for (Child child : children) {
            child.setAverageScore(AverageScoreStrategyFactory.
                    createAverageScoreStrategy(child).getNiceScore(child));
            child.addNiceScoreBonus();
        }
    }

    /**
     * applies strategy by the simulation's year
     * @param flowYear
     */
    public static void chooseStrategyByYear(final int flowYear) {
        if (flowYear == -1) {
            new IdGiftsStrategy().shareGifts();
        } else {
            AnnualChanges annualChanges = Database.getDatabase().getAnnualChanges()
                    .get(flowYear);
            GiftsStrategyFactory.createGiftsStrategy(annualChanges.getStrategy()).shareGifts();
        }
    }
}
