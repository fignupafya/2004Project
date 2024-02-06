package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class memorization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorization);
        Toolbar toolbar = findViewById(R.id.toolbar_memorization);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        if (MainMenu.lang!="English"){
            TextView text1 = findViewById(R.id.textView_memorization);
            TextView text2 = findViewById(R.id.textView_memorization_2);
            Button button = findViewById(R.id.button_memorization);
            button.setText("Başlat");

            getSupportActionBar().setTitle("Hafıza Oyunu");
            text1.setText("Hafıza Oyunu");
            text2.setText("Ekranda gösterilen rakamları aklında tut ve gir");
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

    public void start(View view) {
        Intent intent = new Intent(this, memorization_game.class);
        startActivity(intent);
    }
}