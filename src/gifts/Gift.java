package gifts;

import enums.Category;
import fileio.GiftInputData;

public class Gift {

    private String productName;

    private Double price;

    private Category category;

    private int quantity;

    public Gift() {
        this.productName = null;
        this.price = 0.0;
        this.category = null;
    }

    public Gift(final GiftInputData giftInputData) {
        this.productName = giftInputData.getProductName();
        this.price = giftInputData.getPrice();
        this.category = giftInputData.getCategory();
        this.quantity = giftInputData.getQuantity();
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
     * returns gift's quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * sets gift's product name
     * @param productName to be set
     */
    public void setProductName(final String productName) {
        this.productName = productName;
    }

    /**
     * sets gift's price
     * @param price to be set
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * sets gift's category
     * @param category to be set
     */
    public void setCategory(final Category category) {
        this.category = category;
    }

    /**
     * sets gift's quantity
     * @param quantity to be set
     */
    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }
}
