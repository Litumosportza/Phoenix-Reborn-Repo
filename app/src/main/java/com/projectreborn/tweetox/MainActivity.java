package com.projectreborn.tweetox;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterConfig;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class MainActivity extends AppCompatActivity {


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
    public static TwitterLoginButton loginButton;


    //RIP To Our Lost Comrade isLoggedIn



    @SuppressLint("ApplySharedPref")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Twitter.initialize(this);
        /*TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY), getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
*/
        super.onCreate(savedInstanceState);
       // Twitter.initialize(this);
        TwitterConfig config = new TwitterConfig.Builder(this)
                .logger(new DefaultLogger(Log.DEBUG))
                .twitterAuthConfig(new TwitterAuthConfig(getString(R.string.com_twitter_sdk_android_CONSUMER_KEY), getString(R.string.com_twitter_sdk_android_CONSUMER_SECRET)))
                .debug(true)
                .build();
        Twitter.initialize(config);
        //TODO: change to Log.INFO -en el futuro


        //TODO: make settings go back to the profile fragment.

//TODO: Clean up this comment mess!
        // SharedPreferences loginPrefs = getSharedPreferences("Luke", MODE_PRIVATE);
        // SharedPreferences.Editor editor = loginPrefs.edit();
        // editor.putBoolean("isLoggedin", isLoggedIn);
        // editor.commit();

        setContentView(R.layout.login);



        /*BottomNavigationView navView = findViewById(R.id.nav_view);
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

/*


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


*/    loginButton = (TwitterLoginButton) findViewById(R.id.twitterLoginButton);
        loginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                // Do something with result, which provides a TwitterSession for making API calls
                TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
                TwitterAuthToken authToken = session.getAuthToken();
                //String token = authToken.token;
                //  String secret = authToken.secret;

                loginMethod(session);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                Toast.makeText(getApplicationContext(),"Login fail", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void loginMethod(TwitterSession twitterSession){
        String userName = twitterSession.getUserName();
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        intent.putExtra("username",userName);
        startActivity(intent);
    }


    //TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
     //   TwitterAuthToken authToken = session.getAuthToken();
     //   String token = authToken.token;
     //   String secret = authToken.secret;
        //IN THEORY










    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        MainActivity.super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        loginButton.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        finishAffinity();
    }
}




