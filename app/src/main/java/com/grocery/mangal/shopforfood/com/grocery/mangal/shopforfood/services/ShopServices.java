package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.services;

import com.grocery.mangal.shopforfood.R;

/**
 * Created by mangal on 22/1/18.
 */

public class ShopServices {
    private static String[] shopList = {"Mangal Grocery Shop", "Mallesh The family Mart", "Soumya All Variety Store",
    "Londis shop", "Shop Front kids"};
    private static String[] ratings = {"3.5", "4.5", "5", "4.4", "2.0"};
    private static String[] distance = {"500 mtr", "300 mtr", "800 mtr", "400 mtr", "1 km"};
    private static String[] openCloseStatus = {"Open", "Open", "Closed", "Open", "Closed"};
    private static Integer[] images = {R.mipmap.ic_lego_shop, R.mipmap.ic_future_shop, R.mipmap.ic_shop_local,R.mipmap.ic_shop_front,R.mipmap.ic_londis_shop};

    public static String[] getShopList() {
        return shopList;
    }

    public static Integer[] getShopImages() {
        return images;
    }

    public static String[] getRatings() {
        return ratings;
    }

    public static String[] getDistance() {
        return distance;
    }

    public static String[] getOpenCloseStatus() {
        return openCloseStatus;
    }
}
