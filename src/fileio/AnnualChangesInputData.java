package fileio;

import enums.CityStrategyEnum;

import java.util.ArrayList;
import java.util.List;

public class AnnualChangesInputData {

    private final Double newSantaBudget;

    private final List<GiftInputData> newGifts;

    private final List<ChildInputData> newChildren;

    private final List<ChildUpdatesInputData> childrenUpdates;

    private final CityStrategyEnum strategy;

    public AnnualChangesInputData(final Double newSantaBudget, final List<GiftInputData> newGifts,
                                  final List<ChildInputData> newChildren,
                                  final List<ChildUpdatesInputData> childrenUpdates,
                                  final CityStrategyEnum strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = new ArrayList<>(newGifts);
        this.newChildren = new ArrayList<>(newChildren);
        this.childrenUpdates = new ArrayList<>(childrenUpdates);
        this.strategy = strategy;
    }

    /**
     * returns santa's new budget
     */
    public Double getNewSantaBudget() {
        return newSantaBudget;
    }

    /**
     * returns santa's new gifts
     */
    public List<GiftInputData> getNewGifts() {
        return newGifts;
    }

    /**
     * returns santa's new children from list
     */
    public List<ChildInputData> getNewChildren() {
        return newChildren;
    }

    /**
     * returns the updates for children
     */
    public List<ChildUpdatesInputData> getChildrenUpdates() {
        return childrenUpdates;
    }

    /**
     * returns the strategy adopted for sharing the gifts
     */
    public CityStrategyEnum getStrategy() {
        return strategy;
    }
}
