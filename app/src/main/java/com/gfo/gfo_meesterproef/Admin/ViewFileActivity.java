package com.gfo.gfo_meesterproef.Admin;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gfo.gfo_meesterproef.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ViewFileActivity extends AppCompatActivity {

    ListView adminProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);

//        get selected group from ViewGroupActivity
        String group = getIntent().getExtras().getString("group","x");

//        contact database for products
        String type = "view";
        List<String> products = new ArrayList<String>();
        try {
            products = new ViewFileBackgroundWorker(this).execute(type, group).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        fill listView with List
        adminProductList = (ListView) findViewById(R.id.adminProductsList);
        ArrayAdapter<String> productAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, products);
        adminProductList.setAdapter(productAdapter);

    }
}
