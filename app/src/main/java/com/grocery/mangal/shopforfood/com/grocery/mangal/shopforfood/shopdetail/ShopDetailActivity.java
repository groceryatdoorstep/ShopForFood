package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.shopdetail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import com.grocery.mangal.shopforfood.R;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.adapter.GroceryGroupAdapter;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.model.GroceryGroupModel;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.model.GroceryListDetail;

import java.util.ArrayList;

public class ShopDetailActivity extends AppCompatActivity {

    private String shopName;
    private Integer shopImage;
    private ImageView imageViewId;
    private ListView listView;
    private GroceryGroupAdapter groceryGrpAdapter;
    private ArrayList<GroceryGroupModel> groceryModel;

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();

        imageViewId = (ImageView) findViewById(R.id.groceryImageViewId);
        shopName = intent.getStringExtra("selectedShopName");

        getSupportActionBar().setTitle(shopName);

        listView = (ListView) findViewById(R.id.groceryGroupImageId);

        groceryModel = GroceryListDetail.getGroceryList();
        groceryGrpAdapter = new GroceryGroupAdapter(ShopDetailActivity.this, groceryModel);

        listView.setAdapter(groceryGrpAdapter);

        // to get and set the image in the imageview
        /*Bundle bundle = getIntent().getExtras();
        int res = bundle.getInt("selectedShopImage");
        imageViewId.setImageResource(res);*/
    }
}
