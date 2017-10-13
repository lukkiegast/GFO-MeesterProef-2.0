package com.gfo.gfo_meesterproef.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gfo.gfo_meesterproef.R;

public class ViewFileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);

        String type = "view";
        com.gfo.gfo_meesterproef.Admin.ViewFileBackgroundWorker backgroundWorker = new com.gfo.gfo_meesterproef.Admin.ViewFileBackgroundWorker(this);
        backgroundWorker.execute(type);

    }
}
