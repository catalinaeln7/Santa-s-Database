package gifts;

import children.Child;
import database.Database;
import enums.Cities;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NiceScoreCityGiftsStrategy implements GiftsStrategy {
    /**
     * shares the gifts to children in santa's list by city's average score
     */
    @Override
    public void shareGifts() {
        List<Cities> citiesList;
        List<Child> children = Database.getDatabase().getChildren();

        children.sort(Comparator.comparingInt(Child::getId));

        citiesList = calculateCityAverage(children);

        citiesList.sort(Comparator.comparingDouble(Cities::getAverageScore).reversed()
                .thenComparing(Cities::getValue));

        giftsForChildrenInCities(citiesList, children);
    }

    /**
     * calculates the average for every city by children's average score
     * @param children santa's children list
     * @return list of cities sorted by average and then by name
     */
    private List<Cities> calculateCityAverage(final List<Child> children) {
        List<Cities> citiesList = new ArrayList<>();
        Double sum;
        int nr;

        for (Cities city : Cities.values()) {
            sum = 0.0;
            nr = 0;
            for (Child child : children) {
                if (child.getCity() == city) {
                    sum += child.getAverageScore();
                    nr++;
                }
            }
            if (nr == 0) {
                city.setAverageScore(0.0);
            } else {
                city.setAverageScore(sum / nr);
            }

            citiesList.add(city);
        }

        return citiesList;
    }

    /**
     * gives gift to the children from every city in sorted cities list
     * @param citiesList sorted cities list
     * @param children santa's children list
     */
    private void giftsForChildrenInCities(final List<Cities> citiesList,
                                          final List<Child> children) {
        List<Child> cityChildren = new ArrayList<>();

        for (Cities city : citiesList) {
            cityChildren.clear();
            for (Child child : children) {
                if (child.getCity() == city) {
                    cityChildren.add(child);
                }
            }
            cityChildren.sort(Comparator.comparingInt(Child::getId));
            ShareGifts.giveGiftToChildren(cityChildren);
        }
    }
}
