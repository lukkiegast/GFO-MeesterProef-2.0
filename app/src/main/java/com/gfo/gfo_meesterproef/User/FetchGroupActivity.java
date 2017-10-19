package com.gfo.gfo_meesterproef.User;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gfo.gfo_meesterproef.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.gfo.gfo_meesterproef.LoginActivity.contextOfApplication;

public class FetchGroupActivity extends AppCompatActivity {

    ListView userGroupList;
    String selectedGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_group);

//        get saved username
        SharedPreferences usernamePref = getSharedPreferences("usernamePreference", contextOfApplication.MODE_PRIVATE);
        String username = usernamePref.getString("username", "");

//        contact database
        String type = "fetch";
        List<String> groups = new ArrayList<String>();
        try {
            groups = new FetchGroup(this).execute(type, username).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
//        fill listView with (array)List
        userGroupList = (ListView) findViewById(R.id.userGroupList);
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, groups);
        userGroupList.setAdapter(groupAdapter);

        registerGroupClickCallback();
    }

//    select group
    private void registerGroupClickCallback() {
        userGroupList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View viewClicked, int position, long id) {
                TextView textView = (TextView) viewClicked;
                selectedGroup = textView.getText().toString();

                Toast.makeText(FetchGroupActivity.this,
                        selectedGroup, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
//    confirmation for logout
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm Logout");
        builder.setMessage("Are you sure you want to logout?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                FetchGroupActivity.this.finish();
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