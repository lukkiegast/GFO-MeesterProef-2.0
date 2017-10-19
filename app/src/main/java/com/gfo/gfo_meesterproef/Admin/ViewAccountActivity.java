package com.gfo.gfo_meesterproef.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gfo.gfo_meesterproef.R;

public class ViewAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_account);

        String type = "view";
        com.gfo.gfo_meesterproef.Admin.ViewAccountBackgroundWorker backgroundWorker = new com.gfo.gfo_meesterproef.Admin.ViewAccountBackgroundWorker(this);
        backgroundWorker.execute(type);

    }
}
