package fileio;

import enums.ElvesType;

import java.util.ArrayList;
import java.util.List;

public class ChildUpdatesInputData {
    private final int id;
    private final Double niceScore;
    private final List<String> giftPreferences;
    private final ElvesType elf;

    public ChildUpdatesInputData(final int id, final Double niceScore,
                                  final List<String> giftPreferences, final ElvesType elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftPreferences = new ArrayList<>(giftPreferences);
        this.elf = elf;
    }

    /**
     * returns updated child's id
     */
    public int getId() {
        return id;
    }

    /**
     * returns child's updated nice score
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * returns child's updated gift preferences
     */
    public List<String> getGiftPreferences() {
        return giftPreferences;
    }

    /**
     * returns child's elf
     */
    public ElvesType getElf() {
        return elf;
    }
}
