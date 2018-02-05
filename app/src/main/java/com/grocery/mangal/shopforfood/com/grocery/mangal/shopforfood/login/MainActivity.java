package com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.grocery.mangal.shopforfood.ActivityHomePage;
import com.grocery.mangal.shopforfood.LogoutActivity;
import com.grocery.mangal.shopforfood.R;
import com.grocery.mangal.shopforfood.SplashScreenActivity;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.database.DatabaseHandler;

public class MainActivity extends AppCompatActivity {

    private DatabaseHandler db;
    private EditText userName;
    private EditText password;
    private TextView textViewId;
    private Button loginButton;
    private Button registerButton;
    private String loginType;
    private String userNameValue;
    private String userPasswordValue;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //GETTING THE INTENT AFTER REGISTRATION IS DONE SUCCESSFULLY
        Intent intent = getIntent();
        setContentView(R.layout.activity_login);
        String loggedUserName = intent.getStringExtra("loggedUserName");
        String loggedUserEmail = intent.getStringExtra("loggedUserEmail");
        textViewId = (TextView) findViewById(R.id.validationMsgTxt);
        if (loggedUserName != null) {
            doChangeColor(textViewId);
        }

        db = new DatabaseHandler(getApplicationContext());
        userName = (EditText) findViewById(R.id.usernameTxt);
        password = (EditText) findViewById(R.id.passwordTxt);
        loginButton = (Button) findViewById(R.id.loginBtn);
        registerButton = (Button) findViewById(R.id.registerBtn);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                doLogin();
            }
        }); // to login as an existed user
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                doSignUp();
            }
        }); // to sign up as a new user.

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        confirmDialog();
    }

    public void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure you want to Exit the Application? ");
        alertDialog.setTitle("Confirm Exit");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
                System.exit(0);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.create().show();
    }

    //signing as new user
    public void doSignUp() {
        Intent intent = new Intent(this, UserRegistrationActivity.class);
        startActivity(intent);
    }

    //for changing the textview color
    public void doChangeColor(TextView textViewId) {
        textViewId.setText("You have successfuly registered...");
        textViewId.setTextColor(Color.GREEN);
        findViewById(R.id.validationMsgTxt).setVisibility(View.VISIBLE);
    }

    public void onRadioButtonClicked(View view) {
        checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.asBuyer :
                if (checked) {
                    loginType = " as Buyer";
                }
                break;
            case R.id.asSeller :
                if (checked) {
                    loginType = " as Seller";
                }
        }
    }

    @Override
    public boolean supportRequestWindowFeature(int featureId) {
        return super.supportRequestWindowFeature(featureId);
    }

    public void doLogin() {
        String uName = userName.getText().toString();
        String passWord = password.getText().toString();

        if (!checked) {
            Toast.makeText(this, "Please select login type ", Toast.LENGTH_SHORT).show();
        } else if (uName.isEmpty()) {
            //userName.setError("user name cant be blank.");
            userName.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            userName.setHint("Enter user name");
            userName.setHintTextColor(Color.RED);
            userName.requestFocus(); // to get the focus to the current field

        } else if (passWord.isEmpty()) {

            password.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
            password.setHint("Enter password");
            password.setHintTextColor(Color.RED);
            password.requestFocus(); // to get the focus to the current field

        } else {
            String email = db.userValidation(uName,passWord);
            if (String.valueOf(email).length() > 1) {
                Intent intent = new Intent(this, ActivityHomePage.class);
                intent.putExtra("userName", uName);
                intent.putExtra("email", email);
                finish();
                startActivity(intent);
            } else {
                //Toast.makeText(this, "Invalid Username or Password ", Toast.LENGTH_SHORT).show();
                userName.setText("");
                password.setText("");

                textViewId.setText("Invalid username or password");
                textViewId.setTextColor(Color.RED);
                //findViewById(R.id.validationMsgTxt).setVisibility(View.VISIBLE);
                userName.requestFocus(); // to get the focus to the current field
            }

        }
    }

    public DatabaseHandler getDatabaseHandler() {
        return new DatabaseHandler(getApplicationContext());
    }

}
