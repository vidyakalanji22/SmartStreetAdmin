package com.msd.smartstreet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.core.utilities.Tree;
import com.msd.utils.Constants;
import com.msd.utils.TreeInfo;

import java.util.ArrayList;
import java.util.List;

import admin.msd.com.smartstreetadmin.R;

public class TreeMaintenance extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private Firebase mFirebaseRef;
    ListView listView=null;
    private String itemsUrl;
    public static int flag =0 ;
    List<TreeInfo> treeList = new ArrayList<TreeInfo>();
    Button interactionDetails;

    /**
     * Tree maintenance page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_maintenance);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        interactionDetails = (Button) findViewById(R.id.interDet);
        interactionDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TreeMaintenance.this, InteractedData.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(android.R.id.list);
        this.retrieveData();

    }

    private void retrieveData() {
        itemsUrl = Constants.FIREBASE_URL + "/TreeDetails/";
        System.out.println("URL value : " +itemsUrl);
        // Use Firebase to populate the list.
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


    private void getUpdates(DataSnapshot ds){

        TreeInfo treeInfo = ds.getValue(TreeInfo.class);
        treeList.add(treeInfo);


        if(treeList.size()>0){

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(TreeMaintenance.this);
                    builder.setTitle("Tree");

                    // Set up the input
                    final EditText input = new EditText(TreeMaintenance.this);
                    // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                    builder.setView(input);

                    // Set up the buttons
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String m_Text = input.getText().toString();
                            TreeInfo tree = new TreeInfo();
                            tree.setSensorAttached(m_Text);
                            flag = 1;
                        }
                    });
                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    builder.show();

                }
            });

            ArrayAdapter adapter = new TreeArrayAdapter(TreeMaintenance.this,treeList);
            System.out.println("treeList : " + treeList);
            listView.setAdapter(adapter);
        }else{
            Toast.makeText(TreeMaintenance.this, "No data", Toast.LENGTH_SHORT);
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
            Intent intent_home = new Intent(TreeMaintenance.this,AdminHome.class);
            startActivity(intent_home);
        }else if (id == R.id.action_logout) {
            mFirebaseRef.unauth();
            Intent i = new Intent(TreeMaintenance.this, AdminLogin.class);
            startActivity(i);
        }else if (id == R.id.exit) {

        }
        return true;
    }

}
