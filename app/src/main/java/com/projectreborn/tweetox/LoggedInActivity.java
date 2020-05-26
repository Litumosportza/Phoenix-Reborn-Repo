package com.projectreborn.tweetox;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class LoggedInActivity extends AppCompatActivity {

    /**
     * RESOURCES
     * -the forked twitter sdk was added to this project by me, now we just have to use it!
     * - here are some links:
     * <p>
     * https://www.javatpoint.com/android-twitter-integrating
     * https://github.com/twitter-archive/twitter-kit-android/wiki
     * https://stackoverflow.com/questions/12091872/twitter-login-for-android-app
     * <p>
     * those tutorials should get us going.
     * just don't use anything involving flutter because that's old and was replaced by firebase, we don't need it anyway
     */
    public static boolean isLoggedIn = false;

    //RIP To Our Lost Comrade isLoggedIn


    @SuppressLint("ApplySharedPref")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //might need to run this?
        /*
        Twitter.initialize(this); //TODO: change to Log.INFO -en el futuro
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY), getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build();
         Twitter.initialize(config);
       */

        SharedPreferences loginPrefs = getSharedPreferences("Luke", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPrefs.edit();
        editor.putBoolean("isLoggedin", isLoggedIn);
        editor.commit();

        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build(); //e
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration); //no navbar currently being used.
        NavigationUI.setupWithNavController(navView, navController);
          /*  navView.setBackgroundColor(getResources().getColor(R.color.darkGrey));
            navView.setItemRippleColor(ColorStateList.valueOf(Color.WHITE));
            navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
            navView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE)); */


        //Ensures the correct theme loads, upon updating
        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getApplicationContext()));
        String themeSetting = sharedPreference.getString("theme", "dark");
        assert themeSetting != null;
        if (themeSetting.equals("dark")) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_YES);
        } else if (themeSetting.equals("light")) {
            AppCompatDelegate.setDefaultNightMode(
                    AppCompatDelegate.MODE_NIGHT_NO);

        }


    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finishAffinity();
    }

}
