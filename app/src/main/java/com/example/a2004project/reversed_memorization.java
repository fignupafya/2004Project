package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class reversed_memorization extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reversed_memorization);
        Toolbar toolbar = findViewById(R.id.toolbar_reversed_memorization);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        if (MainMenu.lang!="English"){
            TextView text1 = findViewById(R.id.textView_reversed_memorization);
            TextView text2 = findViewById(R.id.textView_reversed_memorization_2);
            Button button = findViewById(R.id.button_reversed_memorization);
            button.setText("Başlat");

            getSupportActionBar().setTitle("Tersten Hatırlama Oyunu");
            text1.setText("Tersten Hatırlama Oyunu");
            text2.setText("Ekranda gösterilen rakamları aklında tutup ters sırada gir");
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
        Intent intent = new Intent(this, reversed_memorization_game.class);
        startActivity(intent);
    }

}