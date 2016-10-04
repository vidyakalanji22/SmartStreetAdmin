package com.msd.smartstreet;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import admin.msd.com.smartstreetadmin.R;

public class AdminLogin extends AppCompatActivity {
    EditText uName, password;
    Button loginButton;

    /**
     * Admin login page
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_login);
        Firebase.setAndroidContext(this);


        uName = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.signin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName, pass;
                userName = uName.getText().toString();
                pass = password.getText().toString();

                if (userName.equalsIgnoreCase(" ") && pass.equalsIgnoreCase(" ")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdminLogin.this);
                    builder.setMessage("Please enter username and password")
                            .setTitle(R.string.error)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }

                if (userName.equalsIgnoreCase("admin") && pass.equalsIgnoreCase("password")) {
                    Intent intent = new Intent(AdminLogin.this, AdminHome.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AdminLogin.this);
                    builder.setMessage("Invalid credentials. Please try again")
                            .setTitle(R.string.error)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

            }
        });


    }
}
