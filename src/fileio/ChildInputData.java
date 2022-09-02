package fileio;

import enums.Cities;
import enums.ElvesType;

import java.util.ArrayList;
import java.util.List;

public class ChildInputData {

    private final int id;

    private final String lastName;

    private final String firstName;

    private final int age;

    private final Cities city;

    private final Double niceScore;

    private final List<String> giftPreferences;

    private final Double niceScoreBonus;

    private final ElvesType elf;

    public ChildInputData(final int id, final String lastName, final String firstName,
                          final int age, final Cities city, final Double niceScore,
                          final List<String> giftPreferences, final Double niceScoreBonus,
                          final ElvesType elf) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.city = city;
        this.niceScore = niceScore;
        this.giftPreferences = new ArrayList<>(giftPreferences);
        this.niceScoreBonus = niceScoreBonus;
        this.elf = elf;
    }

    /**
     * returns child's id
     */
    public int getId() {
        return id;
    }

    /**
     * returns child's last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * returns child's first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * returns child's age
     */
    public int getAge() {
        return age;
    }

    /**
     * returns child's city
     */
    public Cities getCity() {
        return city;
    }

    /**
     * returns child's nice score
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * returns child's gift preferences
     */
    public List<String> getGiftPreferences() {
        return giftPreferences;
    }

    /**
     * returns the nice score bonus of the child
     */
    public Double getNiceScoreBonus() {
        return niceScoreBonus;
    }

    /**
     * returns the child's type of elf
     */
    public ElvesType getElf() {
        return elf;
    }
}
