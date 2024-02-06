package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ball_track_game extends AppCompatActivity {

    ImageView ball;

    Button restart_button ;

// To make the button invisible

    int DURATION = 1000;
    float initialTranslationX;
    float initialTranslationY;
    float initialScaleX;
    float initialScaleY;
    float yPos = 0f;
    int screenWidth;

    protected void animate() {
        // Move the image
        ObjectAnimator moveX = ObjectAnimator.ofFloat(ball, "translationX", screenWidth - ball.getWidth());
        moveX.setDuration(DURATION);

        // Play both animations together
        AnimatorSet animatorSet1 = new AnimatorSet();
        animatorSet1.playTogether(moveX);
        animatorSet1.start();

        animatorSet1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                // Do nothing
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                AnimatorSet animatorSet2 = new AnimatorSet();
                ObjectAnimator moveX2 = ObjectAnimator.ofFloat(ball, "translationX", 0f);
                ObjectAnimator moveY = ObjectAnimator.ofFloat(ball, "translationY", yPos);
                moveY.setDuration(300);
                moveX2.setDuration(200);
                animatorSet2.playTogether(moveY, moveX2);
                animatorSet2.start();
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

    public void cycle() {
        restart_button.setVisibility(View.INVISIBLE);

        Handler handler = new Handler();
        int delay = 1000; // Initial delay of 1 second

        for (int i = 0; i < 8; i++) {
            int I = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Call your function here
                    yPos += ball.getHeight();
                    animate();


                    if (I == 7) {
                        Handler handler = new Handler();
                        // Make the button visible after the last iteration
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                restart_button.setVisibility(View.VISIBLE);

                                SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                String pointid = sharedPreferences.getString("isLoggedIn", "")+"_point_";
                                int point = 5;
                                String stored_userpoint = sharedPreferences.getString(pointid, "");
                                if (!stored_userpoint.equals("")){
                                    point += Integer.parseInt(stored_userpoint);
                                }
                                editor.putString(pointid,Integer.toString(point));
                                editor.apply();
                                Log.d("DEBUG",sharedPreferences.getString(pointid, ""));


                            }
                        }, 1300);
                    }


                }
            }, delay);

            delay += 2000; // Increase the delay by 1 second for each iteration
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_track_game);
        Toolbar toolbar = findViewById(R.id.toolbar_ball_track_game);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        if (MainMenu.lang!="English"){
            getSupportActionBar().setTitle("Topu Takip Etme");
            Button buton;
            buton=findViewById(R.id.restart_button);
            buton.setText("Tekrar");
        }




        ball = findViewById(R.id.ball);
        restart_button = findViewById(R.id.restart_button);

        initialTranslationX = ball.getTranslationX();
        initialTranslationY = ball.getTranslationY();
        initialScaleX = ball.getScaleX();
        initialScaleY = ball.getScaleY();
        screenWidth = getResources().getDisplayMetrics().widthPixels;


        cycle();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back when the home button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void cycle_restart(View view) {
        ball.setTranslationX(initialTranslationX);
        ball.setTranslationY(initialTranslationY);
        yPos = 0f;
        cycle();
    }
}