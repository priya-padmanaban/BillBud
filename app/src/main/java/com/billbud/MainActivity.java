package com.billbud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    static final public String MYPREFS = "myprefs";

    AppInfo appInfo;

    Button Add, Done;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appInfo = AppInfo.getInstance(this);

        String names = "";
        arrayList = new ArrayList<>(Arrays.asList(names));
        txtinput = (EditText)findViewById(R.id.txtinput);
        ListView listView = (ListView)findViewById(R.id.listView);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, arrayList);
        listView.setAdapter(adapter);
        Add = (Button)findViewById(R.id.Add);
        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String newItem = txtinput.getText().toString();
                 arrayList.add(newItem);
                 adapter.notifyDataSetChanged();
            }
        });
        Done = (Button)findViewById(R.id.Done);
    }

        public void onClick(View v) {
            StringBuilder sB = new StringBuilder();
            for(int i = 0; i < arrayList.size(); i++){
                sB.append(arrayList.get(i));
                sB.append(",");
            }
            appInfo.setString(sB.toString(), 1);
            Intent i = new Intent(this, SecondActivity.class);
            startActivity(i);
        }
}
