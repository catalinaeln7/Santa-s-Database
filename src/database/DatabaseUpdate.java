package database;

import children.Child;
import children.ChildUpdates;
import enums.AgeCategory;
import enums.Category;

import java.util.ArrayList;
import java.util.List;

public final class DatabaseUpdate {
    private DatabaseUpdate() { }

    /**
     * adds the changes of a specific year in the database
     * @param year of the simulation
     */
    public static void addChangesToDatabase(final int year) {
        Database database = Database.getDatabase();
        AnnualChanges annualChange = database.getAnnualChanges().get(year);
        List<Child> children = database.getChildren();

        for (Child child : annualChange.getNewChildren()) {
            if (child.getAge() < AgeCategory.YOUNG_ADULT_AGE) {
                children.add(child);
            }
        }

        searchForUpdates(children, annualChange.getChildrenUpdates());

        database.getSantaGiftsList().addAll(annualChange.getNewGifts());
        database.setSantaBudget(annualChange.getNewSantaBudget());
    }

    /**
     * searches for children updates
     * @param children santa's list of children
     * @param childrenUpdates the children updates
     */
    private static void searchForUpdates(final List<Child> children,
                                         final List<ChildUpdates> childrenUpdates) {
        for (Child child : children) {
            child.getReceivedGifts().clear();
            if (childrenUpdates.size() != 0) {
                for (ChildUpdates childUpdates : childrenUpdates) {
                    if (childUpdates.getId() == child.getId()) {
                        applyChildUpdates(child, childUpdates);
                    }
                }
            }
        }
    }

    /**
     * applies updates to child info
     * @param child to be applied the updates to
     * @param childUpdates the updates for child
     */
    private static void applyChildUpdates(final Child child, final ChildUpdates childUpdates) {
        if (childUpdates.getNiceScore() != null) {
            child.getNiceScoreHistory().add(childUpdates.getNiceScore());
        }

        child.setElf(childUpdates.getElf());

        List<Category> update = childUpdates.getGiftPreferences();
        List<Category> preferences = child.getGiftPreferences();

        deleteUpdateDuplicates(update);
        deletePreferencesDuplicates(update, preferences);
        createUpdatedPreferencesList(update, preferences);
    }

    /**
     * deletes gift preferences duplicates from the update
     * @param update list of gift preferences updates
     */
    private static void deleteUpdateDuplicates(final List<Category> update) {
        for (int i = 0; i < update.size() - 1; ++i) {
            for (int j = i + 1; j < update.size(); ++j) {
                if (update.get(i) == update.get(j)) {
                    update.remove(j);
                    j--;
                }
            }
        }
    }

    /**
     * deletes child's gift preferences duplicates
     * @param update list of gift preferences updates
     * @param preferences list of child's gift preferences
     */
    private static void deletePreferencesDuplicates(final List<Category> update,
                                                    final List<Category> preferences) {
        for (Category category : update) {
            for (int i  = 0; i < preferences.size(); ++i) {
                if (category == preferences.get(i)) {
                    preferences.remove(i);
                    i--;
                }
            }
        }
    }

    /**
     * creates the updated list of gift preferences
     * @param update list of gift preferences updates
     * @param preferences list of child's gift preferences
     */
    private static void createUpdatedPreferencesList(final List<Category> update,
                                                     final List<Category> preferences) {
        List<Category> updatedGiftPreferences = new ArrayList<>();
        updatedGiftPreferences.addAll(update);
        updatedGiftPreferences.addAll(preferences);
        preferences.clear();
        preferences.addAll(updatedGiftPreferences);
    }
}
