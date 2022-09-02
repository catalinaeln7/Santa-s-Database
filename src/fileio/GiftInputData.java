package fileio;

import enums.Category;

public class GiftInputData {

    private final String productName;

    private final Double price;

    private final Category category;

    private final int quantity;

    public GiftInputData(final String productName, final Double price,
                         final Category category, final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    /**
     * returns gift's product name
     */
    public String getProductName() {
        return productName;
    }

    /**
     * returns gift's price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * returns gift's category
     */
    public Category getCategory() {
        return category;
    }

    /**
     * returns gift quantity
     */
    public int getQuantity() {
        return quantity;
    }
}
