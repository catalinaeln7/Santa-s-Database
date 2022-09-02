package utils;

import enums.ElvesType;
import enums.Category;
import enums.CityStrategyEnum;
import enums.Cities;
import enums.Fields;
import fileio.ChildInputData;
import fileio.ChildUpdatesInputData;
import fileio.GiftInputData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Utils {

    private Utils() { }

    /**
     * converts json array to string array
     * @param array json array to be converted
     * @return string array
     */
    public static ArrayList<String> convertJSONArray(final JSONArray array) {
        if (array != null) {
            ArrayList<String> finalArray = new ArrayList<>();
            for (Object object : array) {
                finalArray.add((String) object);
            }
            return finalArray;
        } else {
            return null;
        }
    }

    /**
     * converts json array to a gifts array
     * @param jsonNewGifts json array to be converted
     * @return gifts array
     */
    public static List<GiftInputData> convertNewGifts(final JSONArray jsonNewGifts) {
        List<GiftInputData> newGifts = new ArrayList<>();

        for (Object iterator : jsonNewGifts) {
            newGifts.add(new GiftInputData(
                    (String) ((JSONObject) iterator).get(Fields.PRODUCT_NAME),
                    (double) (long) ((JSONObject) iterator).get(Fields.PRICE),
                    stringToCategory((String) ((JSONObject) iterator).get(Fields.CATEGORY)),
                    (int) (long) ((JSONObject) iterator).get(Fields.QUANTITY)
            ));
        }

        return newGifts;
    }

    /**
     * converts json array to a children array
     * @param jsonNewChildren json array to be converted
     * @return children array
     */
    public static List<ChildInputData> convertChildJson(final JSONArray jsonNewChildren) {
        List<ChildInputData> newChildren = new ArrayList<>();

        for (Object iterator : jsonNewChildren) {
            newChildren.add(new ChildInputData(
                    (int) (long) ((JSONObject) iterator).get(Fields.ID),
                    (String) ((JSONObject) iterator).get(Fields.LAST_NAME),
                    (String) ((JSONObject) iterator).get(Fields.FIRST_NAME),
                    (int) (long) ((JSONObject) iterator).get(Fields.AGE),
                    Utils.stringToCity((String) ((JSONObject) iterator).get(Fields.CITY)),
                    (double) (long) ((JSONObject) iterator).get(Fields.NICE_SCORE),
                    Utils.convertJSONArray((JSONArray) ((JSONObject) iterator)
                            .get(Fields.GIFTS_PREFERENCES)),
                    (double) (long) ((JSONObject) iterator).get(Fields.NICE_SCORE_BONUS),
                    Utils.stringToElvesType((String) ((JSONObject) iterator).get(Fields.ELF))
            ));
        }

        return newChildren;
    }

    /**
     * converts json array to a children' updates array
     * @param jsonChildUpdates json array to be converted
     * @return children' updates array
     */
    public static
    List<ChildUpdatesInputData> convertChildrenUpdates(final JSONArray jsonChildUpdates) {
        List<ChildUpdatesInputData> childUpdates = new ArrayList<>();

        for (Object iterator : jsonChildUpdates) {
            if (((JSONObject) iterator).get(Fields.NICE_SCORE) == null) {
                childUpdates.add(new ChildUpdatesInputData(
                        (int) (long) ((JSONObject) iterator).get(Fields.ID),
                        null,
                        Utils.convertJSONArray((JSONArray) ((JSONObject) iterator)
                                .get(Fields.GIFTS_PREFERENCES)),
                        Utils.stringToElvesType((String) ((JSONObject) iterator).get(Fields.ELF))
                ));
            } else {
                childUpdates.add(new ChildUpdatesInputData(
                        (int) (long) ((JSONObject) iterator).get(Fields.ID),
                        (double) (long) ((JSONObject) iterator).get(Fields.NICE_SCORE),
                        Utils.convertJSONArray((JSONArray) ((JSONObject) iterator)
                                .get(Fields.GIFTS_PREFERENCES)),
                        Utils.stringToElvesType((String) ((JSONObject) iterator).get(Fields.ELF))
                ));
            }
        }

        return childUpdates;
    }

    /**
     * converts string to category enum
     * @param category string to be converted
     * @return category enum
     */
    public static Category stringToCategory(final String category) {
        return switch (category) {
            case "Board Games" -> Category.BOARD_GAMES;
            case "Books" -> Category.BOOKS;
            case "Clothes" -> Category.CLOTHES;
            case "Sweets" -> Category.SWEETS;
            case "Technology" -> Category.TECHNOLOGY;
            case "Toys" -> Category.TOYS;
            default -> null;
        };
    }

    /**
     * converts string to city enum
     * @param city string to be converted
     * @return city enum
     */
    public static Cities stringToCity(final String city) {
        return switch (city) {
            case "Bucuresti" -> Cities.BUCURESTI;
            case "Constanta" -> Cities.CONSTANTA;
            case "Buzau" -> Cities.BUZAU;
            case "Timisoara" -> Cities.TIMISOARA;
            case "Cluj-Napoca" -> Cities.CLUJ;
            case "Iasi" -> Cities.IASI;
            case "Craiova" -> Cities.CRAIOVA;
            case "Brasov" -> Cities.BRASOV;
            case "Braila" -> Cities.BRAILA;
            case "Oradea" -> Cities.ORADEA;
            default -> null;
        };
    }

    /**
     * converts string to elves type enum
     * @param elvesType string to be converted
     * @return elves type enum
     */
    public static ElvesType stringToElvesType(final String elvesType) {
        return switch (elvesType) {
            case "black" -> ElvesType.BLACK;
            case "pink" -> ElvesType.PINK;
            case "white" -> ElvesType.WHITE;
            case "yellow" -> ElvesType.YELLOW;
            default -> null;
        };
    }

    /**
     * converts string to strategy enum
     * @param strategy string to be converted
     * @return strategy enum
     */
    public static CityStrategyEnum stringToStrategy(final String strategy) {
        return switch (strategy) {
            case "id" -> CityStrategyEnum.ID;
            case "niceScore" -> CityStrategyEnum.NICE_SCORE;
            case "niceScoreCity" -> CityStrategyEnum.NICE_SCORE_CITY;
            default -> null;
        };
    }

    /**
     * deletes files from directory
     * @param directory to be deleted from
     */
    public static void deleteFiles(final File[] directory) {
        if (directory != null) {
            for (File file : directory) {
                if (!file.delete()) {
                    System.out.println("did not delete");
                }
            }
        }
    }
}
