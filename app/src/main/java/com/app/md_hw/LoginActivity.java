package com.app.md_hw;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends Activity {
    // Declare Variables
    Button loginbutton;
    Button signup;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;

    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable Local Datastore.
        Parse.initialize(this, "pizEYCj8zYu8dcQ9AU7DxfRqGjx6Lt9Ie2aHV8Rn", "5e9eoQeZiL8yKntAe9tRmRsp3Xe7l9fLShGwoz7E");

        // testing connection with parse.com
        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */

        // Get the view from activity_login.xml
        setContentView(R.layout.activity_login);
        // Locate EditTexts in activity_login.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        // Locate Buttons in main.xml
        loginbutton = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);

        // Login Button Click Listener
        loginbutton.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                if(isNetworkConnected()) {
                    // Retrieve the text entered from the EditText
                    usernametxt = username.getText().toString();
                    passwordtxt = password.getText().toString();

                    // Send data to Parse.com for verification
                    ParseUser.logInInBackground(usernametxt, passwordtxt,
                            new LogInCallback() {
                                public void done(ParseUser user, ParseException e) {
                                    if (user != null) {
                                        // If user exist and authenticated, send user to HomeActivity
                                        Intent intent = new Intent(
                                                LoginActivity.this,
                                                HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(
                                                getApplicationContext(),
                                                "No such user exist, please signup",
                                                Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Device is not connected to the internet. You cannot log in.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        // Sign up Button Click Listener
        signup.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                if(isNetworkConnected()) {
                    // Retrieve the text entered from the EditText
                    usernametxt = username.getText().toString();
                    passwordtxt = password.getText().toString();

                    // Force user to fill up the form
                    if (usernametxt.equals("") || passwordtxt.equals("")) {
                        Toast.makeText(getApplicationContext(),
                                "Please complete the sign up form",
                                Toast.LENGTH_LONG).show();

                    } else {
                        // Save new user data into Parse.com Data Storage
                        ParseUser user = new ParseUser();
                        user.setUsername(usernametxt);
                        user.setPassword(passwordtxt);
                        user.signUpInBackground(new SignUpCallback() {
                            public void done(ParseException e) {
                                if (e == null) {
                                    // Show a simple Toast message upon successful registration
                                    Toast.makeText(getApplicationContext(),
                                            "Successfully Signed up, please log in.",
                                            Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),
                                            "Sign up Error", Toast.LENGTH_LONG)
                                            .show();
                                }
                            }
                        });
                    }
                }else{
                    Toast.makeText(
                            getApplicationContext(),
                            "Device is not connected to the internet. You cannot log in.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }
}

