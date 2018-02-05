package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.model;

/**
 * Created by mangal on 27/1/18.
 */

public class GroceryGroupModel {
    private int groceryImage;
    private String groceryGroupName;
    private String offer;
    private String grocerGroupDesc;

    public GroceryGroupModel(int groceryImage, String groceryGroupName, String offer, String grocerGroupDesc) {
        this.groceryImage = groceryImage;
        this.groceryGroupName = groceryGroupName;
        this.offer = offer;
        this.grocerGroupDesc = grocerGroupDesc;
    }

    public int getGroceryImage() {
        return groceryImage;
    }

    public void setGroceryImage(int groceryImage) {
        this.groceryImage = groceryImage;
    }

    public String getGroceryGroupName() {
        return groceryGroupName;
    }

    public void setGroceryGroupName(String groceryGroupName) {
        this.groceryGroupName = groceryGroupName;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getGrocerGroupDesc() {
        return grocerGroupDesc;
    }

    public void setGrocerGroupDesc(String grocerGroupDesc) {
        this.grocerGroupDesc = grocerGroupDesc;
    }
}
