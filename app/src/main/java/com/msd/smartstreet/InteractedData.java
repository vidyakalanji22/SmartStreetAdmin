package com.msd.smartstreet;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.msd.utils.Constants;
import com.msd.utils.InteractRecords;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import admin.msd.com.smartstreetadmin.R;

public class InteractedData extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    List<InteractRecords> interactRecordsList = new ArrayList<InteractRecords>();
    ListView listView;
    private Map<String, Object> mUserId;
    private String itemsUrl;

    private Firebase mFirebaseRef;

    /**
     * Sets the custom list view for interact data
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interacted_data);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        //  navigationView.getMenu().findItem(R.id.action_signin).setVisible(false);
        navigationView.setNavigationItemSelectedListener(this);

        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        try {
            mUserId = mFirebaseRef.getAuth().getProviderData();
            System.out.println("User ProviderData : " + mUserId);
        } catch (Exception e) {

        }

        listView = (ListView) findViewById(android.R.id.list);
        this.retrieveSharedData();

    }

    private void retrieveSharedData() {

        itemsUrl = Constants.FIREBASE_URL + "/InteractionRecords/";
        System.out.println("URL value : " + itemsUrl);

        new Firebase(itemsUrl)
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        getUpdates(dataSnapshot);
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        getUpdates(dataSnapshot);
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
    }

    private void getUpdates(DataSnapshot ds) {


        InteractRecords records = ds.getValue(InteractRecords.class);
        interactRecordsList.add(records);


        if (interactRecordsList.size() > 0) {
            ArrayAdapter adapter = new InteractArrayAdapter(InteractedData.this, interactRecordsList);
            //ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, shareFirebaseList);
            System.out.println("shareList : " + interactRecordsList);
            listView.setAdapter(adapter);
        } else {
            Toast.makeText(InteractedData.this, "No data", Toast.LENGTH_SHORT);
        }
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
        if (id == R.id.home) {
            // Handle the camera action
            Intent intent_home = new Intent(InteractedData.this, AdminHome.class);
            startActivity(intent_home);
        } else if (id == R.id.action_logout) {
            mFirebaseRef.unauth();
            Intent i = new Intent(InteractedData.this, AdminLogin.class);
            startActivity(i);
        } else if (id == R.id.exit) {

        }

        return true;
    }
}
