package com.gfo.gfo_meesterproef.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.gfo.gfo_meesterproef.R;

public class AddAccountActivity extends AppCompatActivity {

    EditText usernamecET, passwordcET, emailcET, admincET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);
        usernamecET = (EditText) findViewById(R.id.editTextUsername);
        passwordcET = (EditText) findViewById(R.id.editTextPassword);
        emailcET = (EditText) findViewById(R.id.editTextEmail);
        admincET = (EditText) findViewById(R.id.editTextAdmin);
    }

    public void addUser(View view) {
        String usernamec = usernamecET.getText().toString();
        String passwordc = passwordcET.getText().toString();
        String emailc = emailcET.getText().toString();
        String adminflagc = admincET.getText().toString();
        String type = "add_account";

//        stuurt data naar backgroundworker
        AddAccountBackgroundWorker backgroundWorker = new AddAccountBackgroundWorker(this);
        backgroundWorker.execute(type, usernamec, passwordc, emailc, adminflagc);
        ((EditText) findViewById(R.id.editTextUsername)).setText("");
        ((EditText) findViewById(R.id.editTextPassword)).setText("");
        ((EditText) findViewById(R.id.editTextEmail)).setText("");
        ((EditText) findViewById(R.id.editTextAdmin)).setText("");
    }


}

