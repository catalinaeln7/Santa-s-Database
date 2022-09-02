package gifts;

import children.Child;
import database.Database;

import java.util.Comparator;
import java.util.List;

public class IdGiftsStrategy implements GiftsStrategy {
    /**
     * shares the gifts to children in santa's list by id
     */
    @Override
    public void shareGifts() {
        List<Child> children = Database.getDatabase().getChildren();
        children.sort(Comparator.comparingInt(Child::getId));

        ShareGifts.giveGiftToChildren(children);
    }
}
