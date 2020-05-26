package com.projectreborn.tweetox.ui.notifications;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import com.projectreborn.tweetox.MainActivity;
import com.projectreborn.tweetox.R;
import com.projectreborn.tweetox.SettingsActivity;

import java.util.Objects;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.preference.PreferenceManager;


public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel; //THIS IS PROFILE

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(R.string.profileTitle);


            }
        });
        return root;
        //LMAO



    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);



    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        // Inflate the menu items for use in the action bar
        inflater.inflate(R.menu.settings_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.item1);

        SharedPreferences sharedPreference = PreferenceManager.getDefaultSharedPreferences(Objects.requireNonNull(getContext()));
        String themeSetting = sharedPreference.getString("theme", "dark");
        assert themeSetting != null;
        if(themeSetting.equals("dark")){
            tintMenuIcon(Objects.requireNonNull(getContext()), menuItem, R.color.white);
        }
        else if(themeSetting.equals("light")){
            tintMenuIcon(Objects.requireNonNull(getContext()), menuItem, R.color.black);

        }



        super.onCreateOptionsMenu(menu, inflater);


        System.out.println("int x =2");




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item1) {
            Intent intent = new Intent(getContext(), SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public static void tintMenuIcon(Context context, MenuItem item, @ColorRes int color) {
        Drawable normalDrawable = item.getIcon();
        Drawable wrapDrawable = DrawableCompat.wrap(normalDrawable);
        DrawableCompat.setTint(wrapDrawable, context.getResources().getColor(color));

        item.setIcon(wrapDrawable);
    }
}
