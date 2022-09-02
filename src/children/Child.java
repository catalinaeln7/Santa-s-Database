package children;

import common.Constants;
import enums.Category;
import enums.Cities;
import enums.ElvesType;
import fileio.ChildInputData;
import gifts.Gift;

import java.util.ArrayList;
import java.util.List;

public class Child {
    private final int id;

    private final String lastName;

    private final String firstName;

    private final Cities city;

    private int age;

    private final Double niceScore;

    private final List<Category> giftPreferences;

    private Double averageScore;

    private List<Double> niceScoreHistory;

    private Double assignedBudget;

    private List<Gift> receivedGifts;

    private String ageCategory;

    private final Double niceScoreBonus;

    private ElvesType elf;

    public Child(final ChildBuilder builder) {
        this.id = builder.id;
        this.lastName = builder.lastName;
        this.firstName = builder.firstName;
        this.city = builder.city;
        this.age = builder.age;
        this.niceScore = builder.niceScore;

        this.giftPreferences = new ArrayList<>();
        giftPreferences.addAll(builder.giftPreferences);

        this.averageScore = builder.averageScore;

        this.niceScoreHistory = builder.niceScoreHistory;

        this.assignedBudget = builder.assignedBudget;
        this.receivedGifts = builder.receivedGifts;
        this.ageCategory = null;
        this.niceScoreBonus = builder.niceScoreBonus;
        this.elf = builder.elf;
    }

    /**
     * adds the bonus to the nice score
     */
    public void addNiceScoreBonus() {
        averageScore += averageScore * niceScoreBonus / Constants.PERCENTAGE;
        if (averageScore > Constants.MAX_NICE_SCORE) {
            averageScore = Constants.MAX_NICE_SCORE;
        }
    }

    /**
     * makes child grow by one year
     */
    public void grow() {
        age++;
    }

    /**
     * returns the child's id
     */
    public int getId() {
        return id;
    }

    /**
     * returns the child's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * returns the child's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * returns the child's age
     */
    public int getAge() {
        return age;
    }

    /**
     * returns the child's city
     */
    public Cities getCity() {
        return city;
    }

    /**
     * returns the child's nice score
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * returns the child's gift preferences
     */
    public List<Category> getGiftPreferences() {
        return giftPreferences;
    }

    /**
     * returns the child's average nice score
     */
    public Double getAverageScore() {
        return averageScore; }

    /**
     * returns the child's nice score history
     */
    public List<Double> getNiceScoreHistory() {
        return niceScoreHistory; }

    /**
     * returns the child's assigned budget
     */
    public Double getAssignedBudget() {
        return assignedBudget;
    }

    /**
     * returns the child's received gifts
     */
    public List<Gift> getReceivedGifts() {
        return receivedGifts;
    }

    /**
     * returns the child's age category: baby, kid, teen, young adult
     */
    public String getAgeCategory() {
        return ageCategory;
    }

    /**
     * returns the child's nice score bonus
     */
    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * returns the child's elf
     */
    public ElvesType getElf() {
        return elf;
    }

    /**
     * sets the child's average score
     * @param averageScore to be set for child
     */
    public void setAverageScore(final Double averageScore) {
        this.averageScore = averageScore;
    }

    /**
     * sets the child's nice score history
     * @param niceScoreHistory to be set for child
     */
    public void setNiceScoreHistory(final List<Double> niceScoreHistory) {
        this.niceScoreHistory = niceScoreHistory;
    }

    /**
     * sets the child's assigned budget
     * @param assignedBudget to be set for child
     */
    public void setAssignedBudget(final Double assignedBudget) {
        this.assignedBudget = assignedBudget;
    }

    /**
     * sets the child's received gifts
     * @param receivedGifts to be set for child
     */
    public void setReceivedGifts(final List<Gift> receivedGifts) {
        this.receivedGifts = receivedGifts;
    }

    /**
     * sets the child's age category: baby, kid, teen, young adult
     * @param ageCategory to be set for child
     */
    public void setAgeCategory(final String ageCategory) {
        this.ageCategory = ageCategory;
    }

    /**
     * sets child's elf
     * @param elf to be set for child
     */
    public void setElf(final ElvesType elf) {
        this.elf = elf;
    }

    public static class ChildBuilder {

        private final int id;

        private final String lastName;

        private final String firstName;

        private final Cities city;

        private final int age;

        private final Double niceScore;

        private final List<Category> giftPreferences;

        private final Double averageScore = 0.0;

        private final List<Double> niceScoreHistory = new ArrayList<>();

        private final Double assignedBudget = 0.0;

        private final List<Gift> receivedGifts = new ArrayList<>();

        private final String ageCategory = null;

        private Double niceScoreBonus;

        private final ElvesType elf;

        public ChildBuilder(final ChildInputData childInputData) {
            this.id = childInputData.getId();
            this.lastName = childInputData.getLastName();
            this.firstName = childInputData.getFirstName();
            this.city = childInputData.getCity();
            this.age = childInputData.getAge();
            this.niceScore = childInputData.getNiceScore();
            this.giftPreferences = new ArrayList<>();
            for (String gift : childInputData.getGiftPreferences()) {
                giftPreferences.add(Category.getEnum(gift));
            }
            this.niceScoreHistory.add(niceScore);
            this.elf = childInputData.getElf();
        }

        /**
         * constructor for optional field
         * @param builderNiceScoreBonus value for child's bonus
         */
        public ChildBuilder niceScoreBonus(final Double builderNiceScoreBonus) {
            this.niceScoreBonus = builderNiceScoreBonus;
            return this;
        }

        /**
         * creates child by mandatory or optional fields
         */
        public Child build() {
            return new Child(this);
        }
    }
}
