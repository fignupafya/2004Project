package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class days_education extends AppCompatActivity {

    int page;
    int max_page = 9;

    String namee = "days_";

    TextView education_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_days_education);
        page=1;
        education_text = findViewById(R.id.days_text);
        Toolbar toolbar = findViewById(R.id.toolbar_days);

        if (MainMenu.lang != "English"){

            namee = "tr_days_";
            toolbar.setTitle("Günler");
            education_text.setText("Hadi günleri öğrenelim!");
        }

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void forw(View view) {
        if (page != max_page) {
            page++;
            String ResourceName = namee + page;
            int resourceId = getResources().getIdentifier(ResourceName, "string", getPackageName());
            String text = getResources().getString(resourceId);
            education_text.setText(text);
            ScrollView scrollView = findViewById(R.id.days_scroll);
            scrollView.scrollTo(0, 0);
        }
    }

    public void back_days(View view) {
        if (page != 1) {
            page--;
            String ResourceName = namee + page;
            int resourceId = getResources().getIdentifier(ResourceName, "string", getPackageName());
            String text = getResources().getString(resourceId);
            education_text.setText(text);
            ScrollView scrollView = findViewById(R.id.days_scroll);
            scrollView.scrollTo(0, 0);
        }
    }

    public void rover_func(View view) {
        ImageView roverr = findViewById(R.id.rover_days);
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