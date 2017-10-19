package com.gfo.gfo_meesterproef.Admin;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gfo.gfo_meesterproef.R;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

//    intents to other activities
    public void AddUserActivity (View view){
    Intent i = new Intent(this, AddAccountActivity.class);
        startActivity(i);
    }
    public void ViewUserActivity (View view){
        Intent i = new Intent(this, ViewAccountActivity.class);
        startActivity(i);
    }
    public void ViewProductActivity (View view){
        Intent i = new Intent(this, ViewGroupActivity.class);
        startActivity(i);
    }

//    confirmation for logout
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                AdminActivity.this.finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}


