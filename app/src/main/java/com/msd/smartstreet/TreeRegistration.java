package com.msd.smartstreet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.msd.utils.Constants;
import com.msd.utils.TreeInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import admin.msd.com.smartstreetadmin.R;

public class TreeRegistration extends AppCompatActivity {

    /* References to the Firebase */
    private Firebase mFirebaseRef;
    EditText treeNumber,treeName,sensorAttached;
    Button btnReister;

    /**
     * Tree registration page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tree_registration);

        Firebase.setAndroidContext(this);

        /**
         * Create Firebase references
         */
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        treeNumber = (EditText) findViewById(R.id.serialNumber);
        treeName = (EditText) findViewById(R.id.treeName);
        sensorAttached = (EditText) findViewById(R.id.sensor);

        btnReister = (Button) findViewById(R.id.btnRegister);

        btnReister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String treeNum, name, sensor;
                    treeNum = treeNumber.getText().toString();
                    name = treeName.getText().toString();
                    sensor = sensorAttached.getText().toString();


                    TreeInfo info = new TreeInfo();
                    info.setTreeNumber(treeNum);
                    info.setTreeName(name);
                    info.setSensorAttached(sensor);
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                    info.setInstalledDate(sdf.format(new Date()));

                    mFirebaseRef.child("TreeDetails").push().setValue(info);

                    AlertDialog.Builder builder = new AlertDialog.Builder(TreeRegistration.this);
                    builder.setMessage("Successfully registered the Tree")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(TreeRegistration.this, TreeMaintenance.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();

                } catch (Exception e) {
                    Toast.makeText(TreeRegistration.this, "Something went wrong while registering the sensor", Toast.LENGTH_SHORT);
                }
            }
        });
    }
}
