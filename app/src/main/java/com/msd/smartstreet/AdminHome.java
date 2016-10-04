package com.msd.smartstreet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;
import com.msd.utils.Constants;

import admin.msd.com.smartstreetadmin.R;

public class AdminHome extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Button adminProfile, sensorReg, treeReg, sensorMaintenance, treeMaintenance;
    private Firebase mFirebaseRef;

    /**
     * Admin home page. Initializes all the buttons and adds listeners to them
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Admin Home");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.getMenu().findItem(R.id.home).setVisible(false);
        navigationView.setNavigationItemSelectedListener(this);

        adminProfile = (Button) findViewById(R.id.adminprofile);
        sensorReg = (Button) findViewById(R.id.sensorreg);
        treeReg = (Button) findViewById(R.id.treereg);
        sensorMaintenance = (Button) findViewById(R.id.sensormtc);
        treeMaintenance = (Button) findViewById(R.id.treemtc);

        adminProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, AdminProfile.class);
                startActivity(intent);
            }
        });

        sensorReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, Sensor_registration.class);
                startActivity(intent);
            }
        });

        treeReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, TreeRegistration.class);
                startActivity(intent);
            }
        });

        sensorMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, SensorMaintenance.class);
                startActivity(intent);
            }
        });

        treeMaintenance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminHome.this, TreeMaintenance.class);
                startActivity(intent);
            }
        });
    }

    /**
     * adding menu options
     *
     * @param menu
     * @return true or false depending on the menu items
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    /**
     * To perform some action on the menu option selected
     *
     * @param menu
     * @return true or false depending on the menu option selected
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem menu) {

        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = menu.getItemId();


        if (id == R.id.action_logout) {
            Intent intent = new Intent(AdminHome.this, AdminLogin.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menu);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            mFirebaseRef.unauth();
            Intent i = new Intent(AdminHome.this, AdminLogin.class);
            startActivity(i);
        } else if (id == R.id.exit) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
