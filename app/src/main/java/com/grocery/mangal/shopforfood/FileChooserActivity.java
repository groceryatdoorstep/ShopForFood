package com.grocery.mangal.shopforfood;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.login.UserRegistrationActivity;

public class FileChooserActivity extends Activity implements View.OnClickListener {

    ImageView cameraImgId;
    ImageView gallerImgId;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_chooser);
        cameraImgId = (ImageView) findViewById(R.id.cameraImageId);
        gallerImgId = (ImageView) findViewById(R.id.galleryImageId);
        cameraImgId.setOnClickListener(this); // on click method to open camera
        gallerImgId.setOnClickListener(this); // on click method to open gallery
        Toast.makeText(this, "camera button is clicked....", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this, "camera button is clicked", Toast.LENGTH_SHORT).show();
        switch (view.getId()) {
            case R.id.cameraImageId : new UserRegistrationActivity().cameraIntent();
                Toast.makeText(this, "camera button is clicked", Toast.LENGTH_SHORT).show();
                                    break;
            case R.id.galleryImageId : new UserRegistrationActivity().galleryIntent();
                Toast.makeText(this, "gallery button is clicked", Toast.LENGTH_SHORT).show();
                                    break;
        }
    }
}
