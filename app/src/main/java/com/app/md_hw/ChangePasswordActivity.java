package com.app.md_hw;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnonymousUtils;
import com.parse.ParseUser;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by Lau on 13.01.2015.
 */
public class ChangePasswordActivity extends ActionBarActivity {
    //create 2 edittext fields and a new button
    private EditText editNew;
    private EditText editRetype;
    private Button buttonSaveNewPassword;
    String newP, retypeP;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the layout from activity_change_password
        setContentView(R.layout.activity_change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editNew = (EditText) findViewById(R.id.editNew);
        editRetype = (EditText) findViewById(R.id.editRetype);

        buttonSaveNewPassword = (Button) findViewById(R.id.buttonSaveNewPassword);

        // Logout Button Click Listener
        buttonSaveNewPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                newP = editNew.getText().toString().trim();
                retypeP = editRetype.getText().toString().trim();

                ParseUser currentUser = ParseUser.getCurrentUser();
                //if either field is empty return an error
                if(newP == "" || retypeP == ""){
                    Toast.makeText(getApplicationContext(), "You didn't complete all fields", Toast.LENGTH_LONG).show();
                }else{
                //if they are correctly set the new one
                    if (newP.compareTo(retypeP) != 0) {
                        Toast.makeText(getApplicationContext(), "New password and retype new password do not match!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Password was succesfully changed!", Toast.LENGTH_LONG).show();
                        currentUser.setPassword(newP);
                        currentUser.saveInBackground();
                        Intent intent = new Intent(
                                ChangePasswordActivity.this,
                                HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
                }
            });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {

            Intent parentIntent1 = new Intent(this,HomeActivity.class);
            startActivity(parentIntent1);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
