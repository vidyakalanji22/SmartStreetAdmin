package com.msd.smartstreet;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.msd.utils.Constants;
import com.msd.utils.SensorInfo;

import java.text.SimpleDateFormat;
import java.util.Date;

import admin.msd.com.smartstreetadmin.R;

public class Sensor_registration extends AppCompatActivity {
    EditText sensorNumber, treeName, sensorMake, sensorModel;
    Button btnReister;
    /* References to the Firebase */
    private Firebase mFirebaseRef;

    /**
     * Sensor registration page
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_registration);
        Firebase.setAndroidContext(this);

        /**
         * Create Firebase references
         */
        mFirebaseRef = new Firebase(Constants.FIREBASE_URL);

        sensorNumber = (EditText) findViewById(R.id.serialNumber);
        treeName = (EditText) findViewById(R.id.treeName);
        sensorMake = (EditText) findViewById(R.id.make);
        sensorModel = (EditText) findViewById(R.id.model);
        btnReister = (Button) findViewById(R.id.btnRegister);

        btnReister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String sensorNum, name, make, model;
                    sensorNum = sensorNumber.getText().toString();
                    name = treeName.getText().toString();
                    make = sensorMake.getText().toString();
                    model = sensorModel.getText().toString();

                    SensorInfo info = new SensorInfo();
                    info.setSensorNumber(sensorNum);
                    info.setTreeName(name);
                    info.setSensorMake(make);
                    info.setSensorModel(model);
                    info.setStatus("Active");
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                    info.setInstalledDate(sdf.format(new Date()));
                    mFirebaseRef.child("SensorDetails").push().setValue(info);

                    AlertDialog.Builder builder = new AlertDialog.Builder(Sensor_registration.this);
                    builder.setMessage("Successfully registered the sensor")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    Intent intent = new Intent(Sensor_registration.this, SensorMaintenance.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();


                } catch (Exception e) {
                    Toast.makeText(Sensor_registration.this, "Something went wrong while registering the sensor", Toast.LENGTH_SHORT);
                }
            }
        });


    }
}
