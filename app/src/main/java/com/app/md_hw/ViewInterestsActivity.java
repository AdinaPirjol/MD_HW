package com.app.md_hw;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.md_hw.InterestsFragments.MoviesFragment;
import com.app.md_hw.InterestsFragments.MusicFragment;
import com.app.md_hw.InterestsFragments.ProgrammingLanguagesFragment;
import com.parse.FindCallback;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;


public class ViewInterestsActivity extends ActionBarActivity
        implements MusicFragment.OnFragmentInteractionListener,
        MoviesFragment.OnFragmentInteractionListener,
        ProgrammingLanguagesFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // opened up a fragment transaction
        // to add to it all the fragment layouts for each interest
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MusicFragment music = new MusicFragment();
        MoviesFragment movies = new MoviesFragment();
        ProgrammingLanguagesFragment prog_lang = new ProgrammingLanguagesFragment();

        fragmentTransaction.add(R.id.fragment_music, music);
        fragmentTransaction.add(R.id.fragment_movies, movies);
        fragmentTransaction.add(R.id.fragment_prog_lang, prog_lang);

        fragmentTransaction.commit();

        setContentView(R.layout.activity_view_interests);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        changeInterestsListener();
    }

    public void changeInterestsListener(){
        // Create onClick user action for intent of going to the ChangeInterestsActivity
        Button buttonChangeInterests = (Button) findViewById(R.id.buttonChangeInterests);
        buttonChangeInterests.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {

                Intent intent = new Intent(
                        ViewInterestsActivity.this,
                        ChangeInterestsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_interests, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home || id == R.id.up) {

            Intent parentIntent1 = new Intent(this,HomeActivity.class);
            startActivity(parentIntent1);
            return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
