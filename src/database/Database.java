package database;

import children.Child;
import fileio.AnnualChangesInputData;
import fileio.ChildInputData;
import fileio.GiftInputData;
import fileio.Input;
import gifts.Gift;

import java.util.ArrayList;
import java.util.List;

public final class Database {

    private int numberOfYears;

    private Double santaBudget;

    private List<Child> children;

    private List<Gift> santaGiftsList;

    private List<AnnualChanges> annualChanges;

    private static Database instance = null;

    private Database() { }

    /**
     * returns the program's database - singleton
     * @return database's instance
     */
    public static Database getDatabase() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    /**
     * loads all the data in the database
     * @param input to be loaded in database
     */
    public void loadData(final Input input) {
        this.numberOfYears = input.getNumberOfYears();
        this.santaBudget = input.getSantaBudget();
        this.children = loadChildren(input.getChildren());
        this.santaGiftsList = loadSantaGiftsList(input.getSantaGiftsList());
        this.annualChanges = loadAnnualChanges(input.getAnnualChanges());
    }

    /**
     * loads the children in the database
     * @param inputData children to be loaded
     */
    public List<Child> loadChildren(final List<ChildInputData> inputData) {
        List<Child> myChildren = new ArrayList<>();

        for (ChildInputData childInputData : inputData) {
            Child child = new Child.ChildBuilder(childInputData).
                    niceScoreBonus(childInputData.getNiceScoreBonus()).build();
            myChildren.add(child);
        }

        return myChildren;
    }

    /**
     * loads the santa's gifts in the database
     * @param inputData gifts to be loaded
     */
    public List<Gift> loadSantaGiftsList(final List<GiftInputData> inputData) {
        List<Gift> myGifts = new ArrayList<>();

        for (GiftInputData giftInputData : inputData) {
            Gift gift = new Gift(giftInputData);
            myGifts.add(gift);
        }

        return myGifts;
    }

    /**
     * loads the annual changes in the database
     * @param inputData annual changes to be loaded
     */
    public List<AnnualChanges> loadAnnualChanges(final List<AnnualChangesInputData> inputData) {
        List<AnnualChanges> myChanges = new ArrayList<>();

        for (AnnualChangesInputData annualChangesInputData : inputData) {
            AnnualChanges myAnnualChanges = new AnnualChanges(annualChangesInputData);
            myChanges.add(myAnnualChanges);
        }

        return myChanges;
    }

    /**
     * returns the number of years of the simulation
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
    public List<Child> getChildren() {
        return children;
    }

    /**
     * returns santa's gifts list
     */
    public List<Gift> getSantaGiftsList() {
        return santaGiftsList;
    }

    /**
     * returns annual changes in database
     */
    public List<AnnualChanges> getAnnualChanges() {
        return annualChanges;
    }

    /**
     * sets santa's budget
     * @param santaBudget to be set
     */
    public void setSantaBudget(final Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    /**
     * sets santa's list of children
     * @param children to be set
     */
    public void setChildren(final List<Child> children) {
        this.children = children;
    }

    /**
     * sets santa's gifts list
     * @param santaGiftsList to be set
     */
    public void setSantaGiftsList(final List<Gift> santaGiftsList) {
        this.santaGiftsList = santaGiftsList;
    }

}
