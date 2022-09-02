package children;

import enums.Category;
import enums.ElvesType;
import fileio.ChildUpdatesInputData;

import java.util.ArrayList;
import java.util.List;

public class ChildUpdates {
    private final int id;

    private final Double niceScore;

    private final List<Category> giftPreferences;

    private final ElvesType elf;

    public ChildUpdates(final ChildUpdatesInputData childUpdatesInputData) {
        this.id = childUpdatesInputData.getId();
        this.niceScore = childUpdatesInputData.getNiceScore();

        this.giftPreferences = new ArrayList<>();
        for (String gift : childUpdatesInputData.getGiftPreferences()) {
            giftPreferences.add(Category.getEnum(gift));
        }
        this.elf = childUpdatesInputData.getElf();
    }

    /**
     * returns the child's id
     */
    public int getId() {
        return id;
    }

    /**
     * returns the child's updated nice score
     */
    public Double getNiceScore() {
        return niceScore;
    }

    /**
     * returns the child's updated gift preferences
     */
    public List<Category> getGiftPreferences() {
        return giftPreferences;
    }

    /**
     * returns child's elf
     */
    public ElvesType getElf() {
        return elf;
    }
}
