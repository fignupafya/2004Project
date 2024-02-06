package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class similarities_game extends AppCompatActivity {


    int[] answers = {2,2,1,2,1,1,2,1,1,2};
    int index = 1;
    int max = 10;

    String user_point_id;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    String question_name = "similarity_question_";
    String choice_name_1 = "similarity_choice_1_";
    String choice_name_2 = "similarity_choice_2_";

    String all_done = "alldone";
    ImageView question_img;
    ImageView choice_1_img;
    ImageView choice_2_img;
    ImageView tick;
    ImageView cross;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similarities_game);
        sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        user_point_id = sharedPreferences.getString("isLoggedIn", "") + "_point_";
        tick = findViewById(R.id.tick_similarities);
        cross = findViewById(R.id.cross_similarities);
        question_img = findViewById(R.id.similarity_question);
        choice_1_img = findViewById(R.id.similarity_choice_1);
        choice_2_img = findViewById(R.id.similarity_choice_2);
        tick.setVisibility(View.INVISIBLE);
        cross.setVisibility(View.INVISIBLE);

        Toolbar toolbar = findViewById(R.id.similarity_game_toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        show();

        if (MainMenu.lang!="English"){
            all_done="tr_alldone";
            getSupportActionBar().setTitle("Benzerlik Oyunu");
        }



    }

    public void show(){
        choice_1_img.setClickable(true);
        choice_2_img.setClickable(true);
        if (index > max){
            //All done
            int resourceId = getResources().getIdentifier(all_done, "drawable", getPackageName());
            question_img.setImageResource(resourceId);
            choice_1_img.setVisibility(View.INVISIBLE);
            choice_2_img.setVisibility(View.INVISIBLE);
            findViewById(R.id.horizontal_divider_similarities_game).setVisibility(View.INVISIBLE);
            findViewById(R.id.vertical_divider_similarities_game).setVisibility(View.INVISIBLE);
        }
        else {
            String question_resourceName = question_name + index;
            String choice_1_resourceName = choice_name_1 + index;
            String choice_2_resourceName = choice_name_2 + index;
            int question_resourceId = getResources().getIdentifier(question_resourceName, "drawable", getPackageName());
            int choice_1_resourceId = getResources().getIdentifier(choice_1_resourceName, "drawable", getPackageName());
            int choice_2_resourceId = getResources().getIdentifier(choice_2_resourceName, "drawable", getPackageName());
            question_img.setImageResource(question_resourceId);
            choice_1_img.setImageResource(choice_1_resourceId);
            choice_2_img.setImageResource(choice_2_resourceId);
        }
    }

    public void answer(int choice){
        choice_1_img.setClickable(false);
        choice_2_img.setClickable(false);

        if (choice == answers[index-1]){
            //right !!!
            MainActivity.ImageUtils.showAndHideImage(tick, 400);

            int point =  Integer.parseInt(sharedPreferences.getString(user_point_id, "0")) + 5;
            editor.putString(user_point_id, String.valueOf(point));
            editor.apply();
        }
        else{
            //wrong!
            MainActivity.ImageUtils.showAndHideImage(cross, 400);
        }





        Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                index++;
                show();
            }
        };

        handler.postDelayed(runnable, 275);


    }

    public void choice_1(View view) {
        answer(1);
    }

    public void choice_2(View view) {
        answer(2);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back when the home button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}