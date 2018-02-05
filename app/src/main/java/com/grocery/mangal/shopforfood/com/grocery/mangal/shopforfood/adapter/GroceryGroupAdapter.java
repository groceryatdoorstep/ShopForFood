package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.grocery.mangal.shopforfood.R;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.model.GroceryGroupModel;

import java.util.ArrayList;

import static com.grocery.mangal.shopforfood.R.layout.activity_grocery_group_list_view;

/**
 * Created by mangal on 27/1/18.
 */

public class GroceryGroupAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<GroceryGroupModel> groceryGroupModel;

    public GroceryGroupAdapter(Context context, ArrayList<GroceryGroupModel> groceryGroupModel) {
        this.context = context;
        this.groceryGroupModel = groceryGroupModel;
    }

    @Override
    public int getCount() {
        return groceryGroupModel.size();
    }

    @Override
    public Object getItem(int position) {
        return groceryGroupModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = View.inflate(context, R.layout.activity_grocery_group_list_view, null);
        }

        ImageView imageViewId = (ImageView) view.findViewById(R.id.groceryGroupImageId);
        TextView offerId = (TextView) view.findViewById(R.id.offerTxtId);
        TextView groceryGrpId = (TextView) view.findViewById(R.id.groupNameTxtId);
        TextView groceryDesc = (TextView) view.findViewById(R.id.groupDescTxtId);

        GroceryGroupModel groceryModel = groceryGroupModel.get(position);

        Glide.with(view).load(groceryModel.getGroceryImage()).into(imageViewId);

        //imageViewId.setImageResource(groceryModel.getGroceryImage());
        offerId.setText(groceryModel.getOffer());
        groceryGrpId.setText(groceryModel.getGroceryGroupName());
        groceryDesc.setText(groceryModel.getGrocerGroupDesc());

        return view;
    }


}
