package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;

public class MainMenu extends AppCompatActivity {


    Toolbar toolbar;

    public static String lang = "English";



    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("isLoggedIn", "__not_Logged_in__");
        editor.putString("LANGUAGE", "");
        editor.apply();
        MainActivity.lang = "English";
        finish();
    }




    private void set_toolbar_text(){
        SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String userid = sharedPreferences.getString("isLoggedIn", "");
        String user_point = sharedPreferences.getString(userid+"_point_", "0");
        String capitalized_userid = userid.substring(0, 1).toUpperCase() + userid.substring(1);

        if (lang=="English"){
            getSupportActionBar().setTitle(capitalized_userid + "    -    Point: " + user_point);
        }
        else{
            getSupportActionBar().setTitle(capitalized_userid + "    -    Puan: " + user_point);
        }
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);

        MenuItem languageItem = menu.findItem(R.id.action_language);
        ImageView languageImageView = (ImageView) languageItem.getActionView().findViewById(R.id.iv_language);



        PopupMenu popupMenu = new PopupMenu(MainMenu.this, languageImageView);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_language, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.menu_turkish) {
                    lang = "Turkish";
                    set_toolbar_text();


                    SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("LANGUAGE", lang);
                    editor.apply();
                    return true;
                }

                else if (itemId == R.id.menu_english) {
                    lang = "English";
                    set_toolbar_text();


                    SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("LANGUAGE", lang);
                    editor.apply();
                    return true;
                }

                else {
                    return false;
                }

            }
        });

        languageImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
        return true;
    }








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        toolbar = findViewById(R.id.toolbarmainmenu);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setSupportActionBar(toolbar);



        if (MainActivity.lang == "Turkish"){
            lang = "Turkish";
        }

        set_toolbar_text();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 || requestCode == 11) {
            set_toolbar_text();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back when the home button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void learn(View view) {
        Intent intent = new Intent(this, learn_main.class);
        startActivityForResult(intent, 10);
    }


    public void game(View view) {
        Intent intent = new Intent(this, game_main.class);
        startActivityForResult(intent, 11);
    }


}