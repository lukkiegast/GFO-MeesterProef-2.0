package com.gfo.gfo_meesterproef.Admin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gfo.gfo_meesterproef.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewFileActivity extends AppCompatActivity {

    private ListView fileList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);

//        contact database
        String type = "view";
        List<String> groups = new ArrayList<String>();
        try {
            groups = new ViewGroupBackgroundWorker(this).execute(type).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        fill listView with (array)List
        fileList = (ListView) findViewById(R.id.fileList);
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        fileList.setAdapter(groupAdapter);
    }
}
