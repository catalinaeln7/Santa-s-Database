package fileio;

import java.util.List;

public class Input {

    private final int numberOfYears;

    private final Double santaBudget;

    private final List<ChildInputData> children;

    private final List<GiftInputData> santaGiftsList;

    private final List<AnnualChangesInputData> annualChanges;

    public Input() {
        this.numberOfYears = 0;
        this.santaBudget = 0.0;
        this.children = null;
        this.santaGiftsList = null;
        this.annualChanges = null;
    }

    public Input(final int numberOfYears, final Double santaBudget,
                 final List<ChildInputData> children, final List<GiftInputData> santaGiftsList,
                 final List<AnnualChangesInputData> annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.children = children;
        this.santaGiftsList = santaGiftsList;
        this.annualChanges = annualChanges;
    }

    /**
     * returns the simulation's number of years
     */
    public int getNumberOfYears() {
        return numberOfYears;
    }

    /**
     * returns santa's budget
     */
    public Double getSantaBudget() {
        return santaBudget;
    }

    /**
     * returns santa's list of children
     */
    public List<ChildInputData> getChildren() {
        return children;
    }

    /**
     * returns santa's gift list
     */
    public List<GiftInputData> getSantaGiftsList() {
        return santaGiftsList;
    }

    /**
     * returns the list of annual changes
     */
    public List<AnnualChangesInputData> getAnnualChanges() {
        return annualChanges;
    }
}
