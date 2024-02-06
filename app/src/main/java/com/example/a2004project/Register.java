package com.example.a2004project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.content.SharedPreferences;
import android.content.Context;
import android.widget.Button;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Register extends AppCompatActivity {

    String lang ;

    TextView register_tite_text;
    TextView user_id_text;
    TextView password_text;
    Button register_button;

    TextView regerror;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        lang = MainActivity.lang;

        regerror = findViewById(R.id.regerror);
        register_tite_text = findViewById(R.id.register_tite_text);
        user_id_text = findViewById(R.id.register_userid_text);
        password_text = findViewById(R.id.register_password_text);
        register_button = findViewById(R.id.register_register_button);

        if (lang.equals("Turkish")){
            change_lang();
        }

    }

     void change_lang() {
        if (!lang.equals("Turkish")){

            lang = "English";


            register_tite_text.setText("Register");
            password_text.setText("Password");
            user_id_text.setText("Username");
            register_button.setText("Register");
            regerror.setText("Incorrect Username or Password");
            getSupportActionBar().setTitle("Register");

        }
        else{

            lang = "Turkish";

            register_tite_text.setText("Kayıt");
            password_text.setText("Parola");
            user_id_text.setText("Kullanıcı adı");
            register_button.setText("Kayıt ol");
            regerror.setText("Yanlış Parola veya Kullanıcı adı");
            getSupportActionBar().setTitle("Kayıt");

        }
    }



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

    public void Registerfunction(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences("storage", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        TextView useridTextView = findViewById(R.id.loginuserid3);
        TextView passwordTextView = findViewById(R.id.loginpassword3);


        String userid = useridTextView.getText().toString().toLowerCase().strip();
        String password = passwordTextView.getText().toString();

        String stored_userid = sharedPreferences.getString(userid, "");
        if (!stored_userid.equals("") && !userid.endsWith("_point_")){
            if (lang.equals("English")){
                regerror.setText("User already exist");
            }
            else{
                regerror.setText("Böyle bir kullanıcı zaten var");
            }

            regerror.setVisibility(View.VISIBLE);
        }
        else{

            if (userid.equals("") || password.equals("")){
                if (lang.equals("English")){
                    regerror.setText("Username or password can not be empty");
                }
                else{
                    regerror.setText("Kullanıcı adı veya parola boş olamaz");
                }


                regerror.setVisibility(View.VISIBLE);
            }

            else if (userid.endsWith("_point_")){
                if (lang.equals("English")){
                    regerror.setText("Invaild username");
                }
                else{
                    regerror.setText("Geçersiz Kullanıcı adı");
                }


                regerror.setVisibility(View.VISIBLE);
            }

            else {
                password = hashPassword(password);
                editor.putString(userid,password);
                editor.putString("isLoggedIn", userid);
                editor.apply();

                Intent resultIntent = new Intent();
                setResult(RESULT_OK, resultIntent);
                finish();
            }


        }

    }



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


}