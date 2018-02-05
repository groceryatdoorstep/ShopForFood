package com.grocery.mangal.shopforfood;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.login.MainActivity;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.adapter.CustomListViewAdapter;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.services.ShopServices;
import com.grocery.mangal.shopforfood.com.grocery.mangal.shopforfood.shopdetail.ShopDetailActivity;

public class ActivityHomePage extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private int id;
    private TextView textView;
    private TextView userNameId;
    private TextView userEmailId;
    private ListView nearByShopList;
    private Button shopSearchBtn;
    private boolean isHomeEnabled;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //setContentView(R.layout.nav_header_activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Shops near your location");
        textView = (TextView) findViewById(R.id.userLoggedNameTxt);
        shopSearchBtn = (Button) findViewById(R.id.shopSearchBtn);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        String phoneNumber = intent.getStringExtra("mobileNo");
        String email = intent.getStringExtra("email");
        //textView.setText(userName);

        shopSearchBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                getNearByShops();
            }
        });

       /* FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);

        userNameId = view.findViewById(R.id.userLoggedNameTxt);
        userEmailId = view.findViewById(R.id.userLoggedEmailTxt);

        userNameId.setText(userName);
        userEmailId.setText(email);

        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        confirmDialog();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_home_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent intent = new Intent(this, ActivityHomePage.class);
            startActivity(intent);
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_my_order) {

        } else if (id == R.id.nav_logout) {
            confirmDialog();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void confirmDialog() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage("Are you sure you want to logout? ");
        alertDialog.setTitle("Confirm Alert");
        alertDialog.setCancelable(false);

        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(ActivityHomePage.this, MainActivity.class);
                intent.putExtra("logMessage", "You have successfully logged out");
                startActivity(intent);
            }
        });

        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alertDialog.create().show();
    }

    public void getNearByShops() {
        final String[] nearByShops = ShopServices.getShopList();
        final Integer[] flags = ShopServices.getShopImages();
        String[] ratings = ShopServices.getRatings();
        String[] distance = ShopServices.getDistance();
        String[] openCloseStatus = ShopServices.getOpenCloseStatus();
        nearByShopList = (ListView) findViewById(R.id.listViewId);
        final CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(this,nearByShops, ratings, distance, openCloseStatus, flags);
        //ArrayAdapter <String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_listview, R.id.textViewId, nearByShops);
        nearByShopList.setAdapter(customListViewAdapter);
        shopSearchBtn.setVisibility(View.GONE);
        nearByShopList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(ActivityHomePage.this, ShopDetailActivity.class);
                intent.putExtra("selectedShopName", nearByShops[position].toString());
                intent.putExtra("selectedShopImage", flags[position]);
                startActivity(intent);
            }
        });
    }
}
