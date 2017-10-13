package com.gfo.gfo_meesterproef;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

import com.gfo.gfo_meesterproef.Admin.AdminActivity;
import com.gfo.gfo_meesterproef.User.UserActivity;

import java.util.concurrent.ExecutionException;

public class LoginActivity extends AppCompatActivity {

    EditText usernameET, passwordET;

    public static Context contextOfApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameET = (EditText) findViewById(R.id.editTextUser);
        passwordET = (EditText) findViewById(R.id.editTextPass);
//        needed to save username
        contextOfApplication = getApplicationContext();
    }

    public void login(View view) {
        SharedPreferences usernamePref = getSharedPreferences("usernamePreference", contextOfApplication.MODE_PRIVATE);
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();

//        save username
        usernamePref.edit().putString("username", username).apply();

//        contact database
        String type = "login";
        String adminFlag = null;
        try {
            adminFlag = new LoginBackgroundWorker(this).execute(type, username, password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        check adminFlag
        switch (adminFlag) {
            case "ï»¿Y":
                Intent i = new Intent(this, AdminActivity.class);
                this.startActivity(i);
                break;
            case "ï»¿n":
                Intent k = new Intent(this, UserActivity.class);
                this.startActivity(k);
                break;
            default:
                Toast.makeText(this,
                        "Incorrect Username/Password", Toast.LENGTH_LONG).show();
                break;
        }
    }
}