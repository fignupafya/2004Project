package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class similarities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similarities);
        Toolbar toolbar = findViewById(R.id.toolbar_similarities);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        if (MainMenu.lang!="English"){
            TextView text1 = findViewById(R.id.textView_similarities);
            TextView text2 = findViewById(R.id.textView_similarities_2);
            Button button = findViewById(R.id.button_similarities);
            button.setText("Başlat");

            getSupportActionBar().setTitle("Benzerlik Oyunu");
            text1.setText("Benzerlik Oyunu");
            text2.setText("Üstteki resme benzer olanı bulmaya çalış");
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
        Intent intent = new Intent(this, similarities_game.class);
        startActivity(intent);
    }
}