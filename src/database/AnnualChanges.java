package database;

import children.Child;
import children.ChildUpdates;
import enums.CityStrategyEnum;
import fileio.AnnualChangesInputData;
import fileio.ChildInputData;
import fileio.ChildUpdatesInputData;
import fileio.GiftInputData;
import gifts.Gift;

import java.util.ArrayList;
import java.util.List;

public class AnnualChanges {

    private final Double newSantaBudget;

    private final List<Gift> newGifts;

    private final List<Child> newChildren;

    private final List<ChildUpdates> childrenUpdates;

    private CityStrategyEnum strategy;

    public AnnualChanges(final AnnualChangesInputData inputData) {
        this.newSantaBudget = inputData.getNewSantaBudget();

        this.newGifts = new ArrayList<>();
        for (GiftInputData giftInputData : inputData.getNewGifts()) {
            newGifts.add(new Gift(giftInputData));
        }

        this.newChildren = new ArrayList<>();
        for (ChildInputData childInputData : inputData.getNewChildren()) {
            newChildren.add(new Child.ChildBuilder(childInputData).
                    niceScoreBonus(childInputData.getNiceScoreBonus()).build());
        }

        this.childrenUpdates = new ArrayList<>();
        for (ChildUpdatesInputData childUpdatesInputData : inputData.getChildrenUpdates()) {
            childrenUpdates.add(new ChildUpdates(childUpdatesInputData));
        }

        this.strategy = inputData.getStrategy();
    }

    /**
     * returns santa's new budget
     */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * returns the new gifts in santa's list
     */
    public List<Gift> getNewGifts() {
        return newGifts;
    }

    /**
     * returns the new children in santa's list
     */
    public List<Child> getNewChildren() {
        return newChildren;
    }

    /**
     * returns the updates about children
     */
    public List<ChildUpdates> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     * returns the gifts sharing strategy
     */
    public CityStrategyEnum getStrategy() {
        return strategy;
    }
}
