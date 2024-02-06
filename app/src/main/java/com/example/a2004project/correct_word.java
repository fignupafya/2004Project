package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class correct_word extends AppCompatActivity {

    int current = 1;
    int max = 14;

    String[] words = {"elevator", "keyboard", "computer","flower","purse","bucket","skateboard","violin","drone","sailboat","scooter","newspaper","hook","mountain"};


    String[] kelimeler = {"asansör", "klavye", "bilgisayar", "çiçek", "çanta", "kova", "kaykay", "keman", "drone", "yelkenli", "scooter", "gazete", "kanca", "dağ"};

    ImageView correct_word_img;
    ImageView tick;
    ImageView cross;
    TextView textbox;
    String user_point_id;



    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;


    String name = "correct_word_";
    String all_done = "alldone";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_word);
        correct_word_img = findViewById(R.id.correct_word_img);
        tick = findViewById(R.id.tick_word);
        cross = findViewById(R.id.cross_word);
        textbox = findViewById(R.id.text_box_word);
        sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        user_point_id = sharedPreferences.getString("isLoggedIn", "") + "_point_";
        tick.setVisibility(View.INVISIBLE);
        cross.setVisibility(View.INVISIBLE);

        Toolbar toolbar = findViewById(R.id.toolbar_correct_word);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        if (MainMenu.lang!="English"){
            Button buton;
            buton=findViewById(R.id.correct_word_button);
            buton.setText("Tamam");
            getSupportActionBar().setTitle("Doğru Yazılış Oyunu");
            textbox.setHint("Bu nasıl yazılır?");
            all_done="tr_alldone";
        }

    }


    private void ask(){
        if (current < max) {
            current++;
            String resourceName = name + current;
            int resourceId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
            correct_word_img.setImageResource(resourceId);
        }
        else{
            int resourceId = getResources().getIdentifier(all_done, "drawable", getPackageName());
            correct_word_img.setImageResource(resourceId);
            textbox.setVisibility(View.INVISIBLE);
            findViewById(R.id.correct_word_button).setVisibility(View.INVISIBLE);
        }
        textbox.requestFocus();
    }

    public void ok(View view) {
        String userinput = textbox.getText().toString().toLowerCase().strip();
        String answer = words[current - 1];

        if (MainMenu.lang!="English"){
            answer = kelimeler[current - 1];
        }

        if (userinput.equals(answer)){
            textbox.setText("");

            MainActivity.ImageUtils.showAndHideImage(tick, 500);

            int point =  Integer.parseInt(sharedPreferences.getString(user_point_id, "0")) + 5;
            editor.putString(user_point_id, String.valueOf(point));
            editor.apply();
            ask();

        }
        else {
            MainActivity.ImageUtils.showAndHideImage(cross, 500);
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
}