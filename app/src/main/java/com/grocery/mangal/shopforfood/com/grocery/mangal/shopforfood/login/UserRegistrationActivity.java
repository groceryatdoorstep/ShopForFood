package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.login;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.grocery.mangal.shopforfood.ActivityHomePage;
import com.grocery.mangal.shopforfood.FileChooserActivity;
import com.grocery.mangal.shopforfood.R;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.database.DatabaseHandler;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.services.ImageServices;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static android.content.Intent.CATEGORY_APP_GALLERY;
import static android.content.Intent.createChooser;

/**
 * Created by mangal on 14/1/18.
 */


public class UserRegistrationActivity extends Activity implements View.OnClickListener{

    private DatabaseHandler db;
    private ImageButton backButton;
    private Button registerBtn;
    private EditText uNameTxt;
    private EditText mobileNoTxt;
    private EditText passwordTxt;
    private EditText confPasswordTxt;
    private EditText emailTxt;
    private EditText addressTxt;
    private String userName;
    private String mobileNo;
    private String password;
    private String confPassword;
    private String email;
    private String address;
    private String userFlag;
    private String userChoosenTask;
    private ImageView imageView;
    private ImageView imageChangeId;
    BottomSheetDialog bottomSheetDialog;
    private static final int REQUEST_CAMERA = 0, SELECT_FILE = 1;
    private static final int REQUEST_IMAGE_CAPTURE = 1, CATEGORY_APP_GALLERY = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        //Toast.makeText(this, "Welcome to user registration page", Toast.LENGTH_SHORT).show();
        registerBtn = (Button) findViewById(R.id.registerBtn);
        backButton = (ImageButton) findViewById(R.id.backBtn);
        uNameTxt = (EditText) findViewById(R.id.regUserNameTxt);
        mobileNoTxt = (EditText) findViewById(R.id.regMobileNoTxt);
        passwordTxt = (EditText) findViewById(R.id.regPasswordTxt);
        confPasswordTxt = (EditText) findViewById(R.id.regConfPasswordTxt);
        emailTxt = (EditText) findViewById(R.id.regEmailTxt);
        addressTxt = (EditText) findViewById(R.id.regEmailTxt);
        imageView = (ImageView) findViewById(R.id.imageViewBtn);
        imageChangeId = (ImageView) findViewById(R.id.imageChangeId);


        backButton.setOnClickListener(this); //back button to go back.
        imageChangeId.setOnClickListener(this); // profile change button
        registerBtn.setOnClickListener(this); // registration button click functionality
        /*backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                doRegisterUser();
            }
        });

        imageChangeId.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });*/


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.backBtn: doGoBack();
                            break;
            case R.id.registerBtn: doRegisterUser();
                            break;
            case R.id.imageChangeId: chooseImageFrom();;
                            break;
            case R.id.cameraImageId : cameraIntent();
                            break;
            case R.id.galleryImageId : galleryIntent();
                            break;
        }
    }

    public void doGoBack() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void doRegisterUser() {
        db = new DatabaseHandler(this);
        userName = uNameTxt.getText().toString();
        mobileNo = mobileNoTxt.getText().toString();
        password = passwordTxt.getText().toString();
        confPassword = confPasswordTxt.getText().toString();
        email = emailTxt.getText().toString();
        address = addressTxt.getText().toString();

        //creating user
        if(password.trim().equals(confPassword.trim())) {
            if(db.createUser(userName,mobileNo,password,email,address)) {
                userFlag = "Y";
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("loggedUserName", userName);
                intent.putExtra("loggedUserEmail", email);
                intent.putExtra("userFlage", userFlag);
                finish();
                startActivity(intent);
            } else {
                Toast.makeText(this, "Registration failed. Please try again. " + userName, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Password did not match. Plese try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void dispatchTakePictureIntent() {
        chooseImageFrom();
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (resultCode == Activity.RESULT_OK) {
            switch(requestCode) {
                case CATEGORY_APP_GALLERY:
                    try {
                        Uri imageUri = data.getData();
                        InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        //Bitmap circularBitmap = ImageServices.getRoundedCornerBitmap(selectedImage,100);
                        imageView.setImageBitmap(selectedImage);
                        bottomSheetDialog.hide();
                    } catch (IOException e) {

                    }
                    break;
                case REQUEST_CAMERA:
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    Bitmap circularBitmap = ImageServices.getRoundedCornerBitmap(imageBitmap,100);
                    imageView.setImageBitmap(circularBitmap);
                    bottomSheetDialog.hide();
                    break;


            }
        }
        /*if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }*/
    }

    public void chooseImageFrom()  {
       bottomSheetDialog = new BottomSheetDialog(this);
       View bottomSheetView = getLayoutInflater().inflate(R.layout.activity_file_chooser, null);
       bottomSheetDialog.setContentView(bottomSheetView);
       bottomSheetDialog.show();

       ImageView cameraImgId = (ImageView) bottomSheetView.findViewById(R.id.cameraImageId);
       ImageView galleryImgId = (ImageView) bottomSheetView.findViewById(R.id.galleryImageId);

       galleryImgId.setOnClickListener(this);
       cameraImgId.setOnClickListener(this);

        /*final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(UserRegistrationActivity.this);
        builder.setTitle("Change Profile photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                if (items[item].equals("Take Photo")) {
                    userChoosenTask = "Take Photo";
                    cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    userChoosenTask = "Choose from Library";
                    galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
            }
        });
        builder.create().show();*/

    }

    public void cameraIntent() {
        Intent camIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camIntent, REQUEST_CAMERA);
    }

    public void galleryIntent() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, CATEGORY_APP_GALLERY);
    }
}
