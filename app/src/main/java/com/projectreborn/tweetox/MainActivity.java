package com.projectreborn.tweetox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.LabelVisibilityMode;
import com.projectreborn.tweetox.ui.login.LoginActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

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
            navView.setBackgroundColor(getResources().getColor(R.color.darkGrey));
            navView.setItemRippleColor(ColorStateList.valueOf(Color.WHITE));
            navView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);
            navView.setItemIconTintList(ColorStateList.valueOf(Color.WHITE));


        } else {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finishAffinity();
    }

}
