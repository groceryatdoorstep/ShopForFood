package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.grocery.mangal.shopforfood.R;

/**
 * Created by mangal on 22/1/18.
 */

public class CustomListViewAdapter extends ArrayAdapter<String> {
    private String[] shopNames;
    private String[] ratings;
    private String[] distance;
    private String[] openCloseStatus;
    private Integer[] images;
    private Activity context;

    public CustomListViewAdapter(Activity context, String[] shopNames, String[] ratings, String[] distance, String[] openCloseStatus, Integer[] images) {
        super(context, R.layout.activity_listview, shopNames);
        this.context = context;
        this.shopNames = shopNames;
        this.ratings = ratings;
        this.distance = distance;
        this.openCloseStatus = openCloseStatus;
        this.images = images;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null ) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.activity_listview, null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.imageView.setImageResource(images[position]);
        viewHolder.textView.setText(shopNames[position]);
        viewHolder.rating.setText("Rating : " + ratings[position]);
        viewHolder.distance.setText("Distance : " + distance[position]);
        viewHolder.openCloseStatus.setText(openCloseStatus[position]);

        return view;

    }

    class ViewHolder {
        TextView textView;
        ImageView imageView;
        TextView rating;
        TextView distance;
        TextView openCloseStatus;

        ViewHolder (View v) {
            textView = (TextView) v.findViewById(R.id.textViewId);
            imageView = (ImageView) v.findViewById(R.id.shopImageId);
            rating = (TextView) v.findViewById(R.id.ratingViewId);
            distance = (TextView) v.findViewById(R.id.distanceViewId);
            openCloseStatus = (TextView) v.findViewById(R.id.openCloseId);
        }
    }
}
