package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

public class seasons extends AppCompatActivity {


    int page;
    int max_page = 4;

    String namee = "seasons_";

    ImageView education_img;

    ImageView animation_image;

    int DURATION = 400;

    float initialTranslationX;
    float initialTranslationY;
    float initialScaleX;
    float initialScaleY;

    protected void animate(){
        animation_image.setVisibility(View.VISIBLE);

        String animation_resourse = "season_animation_" + page;
        int resourceId = getResources().getIdentifier(animation_resourse, "drawable", getPackageName());
        animation_image.setImageResource(resourceId);


        // Move the image
        ObjectAnimator moveY = ObjectAnimator.ofFloat(animation_image, "translationY", 400f);
        moveY.setDuration(DURATION);

        // Resize the image
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(animation_image, "scaleX", 2f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(animation_image, "scaleY", 2f);
        scaleX.setDuration(DURATION);
        scaleY.setDuration(DURATION);

        // Play both animations together
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(moveY, scaleX, scaleY);
        animatorSet.start();


        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                // Do nothing
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                animation_image.setVisibility(View.INVISIBLE);
                animation_image.setTranslationX(initialTranslationX);
                animation_image.setTranslationY(initialTranslationY);
                animation_image.setScaleX(initialScaleX);
                animation_image.setScaleY(initialScaleY);
            }

            @Override
            public void onAnimationCancel(Animator animator) {
                // Do nothing
            }

            @Override
            public void onAnimationRepeat(Animator animator) {
                // Do nothing
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seasons);
        page = 1;
        education_img = findViewById(R.id.seasons_img);
        Toolbar toolbar = findViewById(R.id.toolbar_seasons);

        if (MainMenu.lang != "English"){
            TextView top_text = findViewById(R.id.seasons_top_text);
            top_text.setText("Mevsimleri öğrenelim!");
            namee = "tr_seasons_";
            toolbar.setTitle("Mevsimler");
            String resourceName = namee + page;
            int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
            education_img.setImageResource(resourceId);
        }

        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        animation_image = findViewById(R.id.animation_image);


        initialTranslationX = animation_image.getTranslationX();
        initialTranslationY = animation_image.getTranslationY();
        initialScaleX = animation_image.getScaleX();
        initialScaleY = animation_image.getScaleY();


        //To fix the animation not showing up on startup
        final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    animate();
                }
            }, 400);


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
            ScrollView scrollView = findViewById(R.id.seasons_scroll);
            scrollView.scrollTo(0, 0);
            animate();
            ConstraintLayout layout_for_bg = findViewById(R.id.seasons_layout_for_bg);
            String bg_resourceName = "seasons_bg_" + page;
            int bg_resourceId = getResources().getIdentifier(bg_resourceName, "drawable", getPackageName());
            layout_for_bg.setBackgroundResource(bg_resourceId);

        }

    }

    public void back(View view) {
        if (page != 1) {
            page--;
            String resourceName = namee + page;
            int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
            education_img.setImageResource(resourceId);
            ScrollView scrollView = findViewById(R.id.seasons_scroll);
            scrollView.scrollTo(0, 0);
            animate();
            ConstraintLayout layout_for_bg = findViewById(R.id.seasons_layout_for_bg);
            String bg_resourceName = "seasons_bg_" + page;
            int bg_resourceId = getResources().getIdentifier(bg_resourceName, "drawable", getPackageName());
            layout_for_bg.setBackgroundResource(bg_resourceId);
        }
    }


    public void rover_func(View view) {
        ImageView roverr = findViewById(R.id.rover_seasons);
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