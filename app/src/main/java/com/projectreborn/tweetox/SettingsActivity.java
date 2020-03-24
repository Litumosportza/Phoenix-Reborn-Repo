package com.projectreborn.tweetox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import java.util.Objects;

public  class SettingsActivity extends AppCompatActivity {
    public static boolean isDarkThemeOn = false;

    public static boolean isLightThemeOn = false;
    public static ListPreference listPreference;
    public static final String
            THEME_SWITCH = "themeType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String themeChoice = sharedPref.getString("theme", "dark");

        Resources res = getResources();
        TypedArray themesInArray = res.obtainTypedArray(R.array.themeVals);
        themesInArray.getString(0)


        switch(themeChoice){
            case Objects.requireNonNull(themesInArray.getString(0)):
//fixing this part
        }

        setTheme(R.style.lightTheme);

        themesInArray.recycle();

        super.onCreate(savedInstanceState);


        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            prefs.registerOnSharedPreferenceChangeListener(spChanged);

        }


    }


    @Override //When you press back, it goes back
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }







    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            listPreference = findPreference("theme");
            SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();



        }


        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
                SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                          String key) {
                        if(listPreference.getValue().equals("dark")){
                            getTheme().applyStyle(R.style.darkTheme, true);
                            setTheme(R.style.darkTheme);
                        }
                        else if(listPreference.getValue().equals("light")){
                            getTheme().applyStyle(R.style.lightTheme, true);
                            setTheme(R.style.lightTheme);
                        }

                       finish();
                       startActivity(getIntent());
                    //FOUNDBOARD
                    }
                };



    public void onBackPressed(){
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    }





