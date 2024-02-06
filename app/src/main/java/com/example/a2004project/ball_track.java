package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ball_track extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_track);
        Toolbar toolbar = findViewById(R.id.toolbar_ball_track);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        if (MainMenu.lang!="English"){
            TextView text1 = findViewById(R.id.textView_ball_track);
            TextView text2 = findViewById(R.id.textView_ball_track_2);
            Button button = findViewById(R.id.button_ball_track);
            button.setText("Başlat");

            getSupportActionBar().setTitle("Topu Takip Etme");
            text1.setText("Topu Takip Etme");
            text2.setText("Ekrandaki topu gözlerinle takip et");
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
        Intent intent = new Intent(this, ball_track_game.class);
        startActivity(intent);
    }
}