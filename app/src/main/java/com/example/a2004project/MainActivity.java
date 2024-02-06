package com.example.a2004project;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Console;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {



    TextView passwordTextView;
    TextView useridTextView;
    TextView lang_text;
    TextView user_id_text;
    TextView password_text;
    Button login_button;
    Button register_button;
    TextView wrongtext;

    public static String lang = "English";

    public String hashPassword(String password) {
        try {
            // Create MessageDigest instance for SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Add password bytes to digest
            md.update(password.getBytes());

            // Get the hashed password bytes
            byte[] hashedBytes = md.digest();

            // Convert the hashed bytes to hexadecimal representation
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }

            // Return the hashed password as a string
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        Uncomment to clear all of the storage!
//        editor.clear();
//        editor.apply();



        String isLoggedIn = sharedPreferences.getString("isLoggedIn", "__not_Logged_in__");

        if (!isLoggedIn.equals("__not_Logged_in__")) {
            String saved_lang = sharedPreferences.getString("LANGUAGE", "");

            if (saved_lang.equals("Turkish")){
                lang = "Turkish";
            }

            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

        setContentView(R.layout.activity_main);

        useridTextView = findViewById(R.id.loginuserid);
        passwordTextView = findViewById(R.id.loginpassword);


        lang_text = findViewById(R.id.lang_text);
        user_id_text = findViewById(R.id.userid_text);
        password_text = findViewById(R.id.password_text);
        login_button = findViewById(R.id.login_button);
        register_button = findViewById(R.id.register_button);
        wrongtext = findViewById(R.id.wrongtext);

        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) { // The request code you used in startActivityForResult for Register

            if (resultCode == RESULT_OK) {
                SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("LANGUAGE", lang);
                editor.apply();

                Intent intent = new Intent(this, MainMenu.class);
                startActivity(intent);
            }

        }
    }


    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivityForResult(intent,2);
    }






    public void login(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();



        String userid = useridTextView.getText().toString().toLowerCase().strip();

        String password = passwordTextView.getText().toString();
        password = hashPassword(password);


        String stored_password = sharedPreferences.getString(userid, "");


        if (!stored_password.equals(password)) {

            wrongtext.setVisibility(View.VISIBLE);
        }
        else {

            editor.putString("isLoggedIn", userid);
            editor.putString("LANGUAGE", lang);
            editor.apply();
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
        }

    }




    public void change_lang(View view) {
        if (lang == "English"){
            lang = "Turkish";

            lang_text.setText("TR");
            password_text.setText("Parola");
            user_id_text.setText("Kullanıcı adı");
            login_button.setText("Giriş yap");
            register_button.setText("Kayıt ol");
            wrongtext.setText("Yanlış Kullanıcı adı veya Parola");

        }
        else{
            lang = "English";


            lang_text.setText("EN");
            password_text.setText("Password");
            user_id_text.setText("Username");
            login_button.setText("Login");
            register_button.setText("Register");
            wrongtext.setText("Incorrect Username or Password");
        }
    }


    public static class ImageUtils {
        public static void showAndHideImage(final ImageView imageView, final int durationMs) {
            // Make the image visible
            imageView.setVisibility(View.VISIBLE);

            // Create a handler to delay hiding the image
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Hide the image after the specified duration
                    imageView.setVisibility(View.GONE);
                }
            }, durationMs);
        }
    }



}