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

public class months extends AppCompatActivity {



    int page;
    int max_page = 12;

    String namee = "months" + "_";

    ImageView education_img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_months);
        page = 1;
        education_img = findViewById(R.id.months_img);
        Toolbar toolbar = findViewById(R.id.toolbar_months);

        if (MainMenu.lang != "English"){
            namee="tr_months_";
            toolbar.setTitle("Aylar");
            TextView directions_top_text = findViewById(R.id.months_top_text);
            directions_top_text.setText("Ayları öğrenelim!");
            String resourceName = namee + page;
            int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
            education_img.setImageResource(resourceId);
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
            String resourceName = namee + page;
            int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
            education_img.setImageResource(resourceId);
            ScrollView scrollView = findViewById(R.id.months_scroll);
            scrollView.scrollTo(0, 0);
        }
        else{
            //new intent call
        }
    }

    public void back(View view) {
        if (page != 1) {
            page--;
            String resourceName = namee + page;
            int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
            education_img.setImageResource(resourceId);
            ScrollView scrollView = findViewById(R.id.months_scroll);
            scrollView.scrollTo(0, 0);
        }
    }

    public void rover_func(View view) {
        ImageView roverr = findViewById(R.id.rover_months);
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