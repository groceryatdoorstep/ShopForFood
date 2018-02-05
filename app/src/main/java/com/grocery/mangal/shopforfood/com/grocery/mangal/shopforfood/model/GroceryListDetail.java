package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.model;

import com.grocery.mangal.shopforfood.R;

import java.util.ArrayList;

/**
 * Created by mangal on 28/1/18.
 */

public class GroceryListDetail {

    public static ArrayList<GroceryGroupModel> getGroceryList() {
        ArrayList<GroceryGroupModel> groceryList = new ArrayList<>();

        groceryList.add(new GroceryGroupModel(R.drawable.grocery_and_staples,"Grocery and Staples","Upto 7 % off", "Grocery and staples offers best here"));
        groceryList.add(new GroceryGroupModel(R.drawable.beverages,  "Beverages", "Upto 15 % off", "Enjoy the variety beverage discounts"));
        groceryList.add(new GroceryGroupModel(R.drawable.house_hold_needs,  "House hold and needs", "Upto 20 % off", "House hold and need provides the best item this season"));
        groceryList.add(new GroceryGroupModel(R.drawable.breakfast_and_dairy,  "Break fast and Dairy", "Upto 12 % off","Grocery and staples offers best here"));
        groceryList.add(new GroceryGroupModel(R.drawable.baby_kids,  "Baby and kids", "Upto 30 % off","Baby and kids all items"));
        groceryList.add(new GroceryGroupModel(R.drawable.grocery_and_staple_new, "New Arrival grocery and staple", "Upto 50% off", "Grocery and staple new arrival fresh"));
        groceryList.add(new GroceryGroupModel(R.drawable.house_hold_needs_new, "New Arrival House hold and needs", "Upto 50% off", "House hold and need new arrival"));

        return groceryList;
    }
}
