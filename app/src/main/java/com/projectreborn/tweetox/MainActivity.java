package com.projectreborn.tweetox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.projectreborn.tweetox.ui.login.LoginActivity;
import com.projectreborn.tweetox.ui.notifications.NotificationsFragment;

import java.util.Objects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    /**
     * RESOURCES
     *  -the forked twitter sdk was added to this project by me, now we just have to use it!
     *  - here are some links:
     *
     * https://www.javatpoint.com/android-twitter-integrating
     * https://github.com/twitter-archive/twitter-kit-android/wiki
     * https://stackoverflow.com/questions/12091872/twitter-login-for-android-app
     *
     * those tutorials should get us going.
     * just don't use anything involving flutter because that's old and was replaced by firebase, we don't need it anyway
     *
     */
    public static boolean isLoggedIn = true;


    @SuppressLint("ApplySharedPref")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        SharedPreferences loginPrefs = getSharedPreferences("Luke", MODE_PRIVATE);
        SharedPreferences.Editor editor = loginPrefs.edit();
        editor.putBoolean("isLoggedin", isLoggedIn);
        editor.commit();

        if (loginPrefs.getBoolean("isLoggedin", isLoggedIn)) {
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


        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

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
