package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class game_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_main);
        Toolbar toolbar = findViewById(R.id.toolbar3);

        if (MainMenu.lang != "English"){
            TextView learn_main_top_text = findViewById(R.id.game_main_top_text);
            learn_main_top_text.setText("Hadi oynayalım!");

            TextView learn_main_rover_text = findViewById(R.id.game_main_rover_text);
            learn_main_rover_text.setText("Hadi oynayalım\nHav   \nHav!  ");
            toolbar.setTitle("Oyunlar");
        }

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        finish();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back when the home button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void memorization(View view) {
        Intent intent = new Intent(this, memorization.class);
        startActivity(intent);
    }

    public void reversed_memorization(View view) {
        Intent intent = new Intent(this, reversed_memorization.class);
        startActivity(intent);
    }

    public void word_game(View view) {
        Intent intent = new Intent(this, word_game.class);
        startActivity(intent);
    }

    public void ball_track(View view) {
        Intent intent = new Intent(this, ball_track.class);
        startActivity(intent);
    }

    public void similarities(View view) {
        Intent intent = new Intent(this, similarities.class);
        startActivity(intent);
    }


    public void rover_func(View view) {
        ImageView roverr = findViewById(R.id.rover_game_main);
        int randomNumber = (int) (Math.floor(Math.random() * 3) + 1);
        switch (randomNumber) {
            case 1:
                float current_Y=roverr.getRotationY();
                PropertyValuesHolder rotationY = PropertyValuesHolder.ofFloat(View.ROTATION_Y, current_Y, current_Y + 180f);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(roverr, rotationY);
                animator.setDuration(400);
                animator.start();
                break;

            case 2:
                PropertyValuesHolder rotationX = PropertyValuesHolder.ofFloat(View.ROTATION_X, 0f, 360f);
                ObjectAnimator animatorX = ObjectAnimator.ofPropertyValuesHolder(roverr, rotationX);
                animatorX.setDuration(400);
                animatorX.start();
                break;
            case 3:
                PropertyValuesHolder rotationZ = PropertyValuesHolder.ofFloat(View.ROTATION, 0f, 360f);
                ObjectAnimator animatorZ = ObjectAnimator.ofPropertyValuesHolder(roverr, rotationZ);
                animatorZ.setDuration(400);
                animatorZ.start();
                break;
        }

    }
}