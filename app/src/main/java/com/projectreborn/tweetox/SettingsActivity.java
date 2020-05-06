package com.projectreborn.tweetox;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Objects;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import com.projectreborn.tweetox.ui.notifications.NotificationsFragment;

public  class SettingsActivity extends AppCompatActivity {
    public static boolean isDarkThemeOn = false;

    public static boolean isLightThemeOn = false;
    public static ListPreference listPreference;
    public static final String THEME_SWITCH = "themeType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
/*
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        String themeChoice = sharedPref.getString("theme", "light");
        switch(themeChoice){
            case "dark":
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case "light":
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO);
                break;
            default:
                AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_YES);
                break;
        }
*/

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getApplicationContext()));
        String themeSetting = sharedPreference.getString("theme", "dark");
        assert themeSetting != null;
        if(themeSetting.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        }
        else if(themeSetting.equals("light")) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);

        }


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


    @Override //When you press back, it goes back (back btns in settings)
    public boolean onSupportNavigateUp() {
        finish();
       Intent intent = new Intent(getApplicationContext(), MainActivity.class);
       startActivity(intent);



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

        SharedPreferences.OnSharedPreferenceChangeListener spChanged = new
                SharedPreferences.OnSharedPreferenceChangeListener() {
                    @Override
                    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                                          String key) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    //FOUNDBOARD
                    }
                };



    public void onBackPressed(){
        super.onBackPressed();
       Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
//TODO: make the back button go to the profile tab (from where we came from)
       // Intent i = new Intent(this, MainActivity.class);
       // i.putExtra("frgToLoad", "fromSettingsToProfile");
       // startActivity(i);

    }


}






