package gifts;

import children.Child;
import database.Database;

import java.util.Comparator;
import java.util.List;

public class NiceScoreGiftsStrategy implements GiftsStrategy {
    /**
     * shares the gifts to children in santa's list by their average score
     */
    @Override
    public void shareGifts() {
        List<Child> children = Database.getDatabase().getChildren();
        children.sort(Comparator.comparingDouble(Child::getAverageScore).reversed()
                .thenComparing(Child::getId));

        ShareGifts.giveGiftToChildren(children);
    }
}
