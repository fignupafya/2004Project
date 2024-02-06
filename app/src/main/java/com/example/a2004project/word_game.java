package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class word_game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_game);
        Toolbar toolbar = findViewById(R.id.toolbar_word_game);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        if (MainMenu.lang!="English"){
            TextView text1 = findViewById(R.id.textView_word_game);
            TextView text2 = findViewById(R.id.textView_word_game_2);
            Button button = findViewById(R.id.button_word_game);
            button.setText("Başlat");

            getSupportActionBar().setTitle("Doğru Yazılış Oyunu");
            text1.setText("Doğru Yazılış Oyunu");
            text2.setText("Resimdekini doğru şekilde yazmaya çalış");
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
        Intent intent = new Intent(this, correct_word.class);
        startActivity(intent);
    }
}