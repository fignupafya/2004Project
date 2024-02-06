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
import android.widget.TextView;


public class memorization_game extends AppCompatActivity {

    TextView textbox;
    TextView input_box;
    private Handler handler;

    boolean showed = false;

    int randomNumber = 0;

    ImageView tick;
    ImageView cross ;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String user_point_id;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back when the home button is pressed
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorization_game);
        textbox = findViewById(R.id.rev_mem_textbox);
        input_box = findViewById(R.id.mem_num_box);
        handler = new Handler();
        sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        user_point_id = sharedPreferences.getString("isLoggedIn", "") + "_point_";
        tick = findViewById(R.id.mem_tick);
        cross = findViewById(R.id.mem_cross);
        tick.setVisibility(View.INVISIBLE);
        cross.setVisibility(View.INVISIBLE);

        Toolbar toolbar = findViewById(R.id.toolbar_memorization_game);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        if (MainMenu.lang!="English"){
            getSupportActionBar().setTitle("Hafıza Oyunu");
            Button buton;
            buton=findViewById(R.id.mem_game_buttonn);
            buton.setText("Tamam");
        }


        randomNumber = (int) (Math.random() * 999999) + 1;
        show();



    }


    private void set_text(String text) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        textbox.setText("");
                    }
                }, 500); // Delay for half a second before clearing the text
                textbox.setText(text);

            }
        },600);
    }


    private void show(){

        showed=false;

        String numberString = String.valueOf(randomNumber);

        // Create an array to store the digits
        int[] digits = new int[numberString.length()];

        // Iterate over each character in the string
        for (int i = 0; i < numberString.length(); i++) {
            // Convert the character back to an integer
            digits[i] = Character.getNumericValue(numberString.charAt(i));
        }

        for (int i = 0; i < digits.length; i++) {
            final int digit = digits[i];
            int I = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    set_text(String.valueOf(digit));
                    if (I ==digits.length-1) {
                        showed = true;
                    }
                }
            }, i * 1200);
        }

    }


    public void evaluate(View view) {
        if (showed){
            String user_answer = input_box.getText().toString();
            if (user_answer.equals(Integer.toString(randomNumber))){
                input_box.setText("");


                MainActivity.ImageUtils.showAndHideImage(tick, 500);


                //Right!
                int point =  Integer.parseInt(sharedPreferences.getString(user_point_id, "0")) + 5;
                editor.putString(user_point_id, String.valueOf(point));
                editor.apply();


                showed = false;
                randomNumber = (int) (Math.random() * 999999) + 1;
                show();
            }
            else{
                //Wrong!

                input_box.setText("");
                MainActivity.ImageUtils.showAndHideImage(cross, 500);
                show();
            }
        }
    }
}