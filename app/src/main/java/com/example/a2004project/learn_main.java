package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class learn_main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);

        if (MainMenu.lang != "English"){
            TextView learn_main_top_text = findViewById(R.id.learn_main_top_text);
            learn_main_top_text.setText("Hadi öğrenelim!");

            TextView learn_main_rover_text = findViewById(R.id.learn_main_rover_text);
            learn_main_rover_text.setText(" Bugün ne \n öğrenmek \n  istersin?");
            toolbar.setTitle("Öğrenme");
            ImageView days = findViewById(R.id.learn_main_days);
            ImageView calendar = findViewById(R.id.calendar_img);
            int resourceId = getResources().getIdentifier("tr_days", "drawable", getPackageName());
            days.setImageResource(resourceId);
            resourceId = getResources().getIdentifier("tr_months", "drawable", getPackageName());
            calendar.setImageResource(resourceId);
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

    public void clock(View view) {
        Intent intent = new Intent(this, clock_education.class);
        startActivity(intent);
    }

    public void days(View view) {
        Intent intent = new Intent(this, days_education.class);
        startActivity(intent);
    }

    public void directions(View view) {
        Intent intent = new Intent(this, directions.class);
        startActivity(intent);
    }

    public void months(View view) {
        Intent intent = new Intent(this, months.class);
        startActivity(intent);
    }

    public void multiplication(View view) {
        Intent intent = new Intent(this, multiplication_education.class);
        startActivity(intent);
    }

    public void seasons(View view) {
        Intent intent = new Intent(this, seasons.class);
        startActivity(intent);
    }

    public void rover_func(View view) {
        ImageView roverr = findViewById(R.id.rover_learn_main);
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