package com.billbud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;

import java.util.*;

/**
 * Created by slapy on 1/20/2018.
 */

public class SecondActivity extends AppCompatActivity{
    AppInfo appInfo;

    EditText it_ed;
    EditText pr_ed;
    ListView lv1;
    ListView lv2;

    Button it_bt;
    Button pr_bt;

    ArrayList<String> items;
    ArrayAdapter<String> it_adapter;
    ArrayList<Double> prices;
    ArrayAdapter<Double> pr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        it_ed = (EditText) findViewById(R.id.et_item);
        pr_ed = (EditText) findViewById(R.id.et_price);
        lv1 = (ListView) findViewById(R.id.lv_item);
        lv2 = (ListView) findViewById((R.id.lv_price));

        it_bt = (Button) findViewById(R.id.it_button);
        pr_bt = (Button) findViewById(R.id.pr_button);

        items = new ArrayList<String>();
        prices = new ArrayList<Double>();

        it_adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                items);
        lv1.setAdapter(it_adapter);
        pr_adapter = new ArrayAdapter<Double>(SecondActivity.this, android.R.layout.simple_list_item_1,
                prices);
        lv2.setAdapter(pr_adapter);

    }

    public void onItBtnClick(View v){
        it_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String input = it_ed.getText().toString();
                items.add(input);
                it_adapter.notifyDataSetChanged();
            }

            });
    }

    public void onPrBtnClick(View v){
        pr_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Double cost;
                String input = pr_ed.getText().toString();
                if(input == null || input.isEmpty()) {
                    cost = 0.0;
                } else {
                    cost = Double.parseDouble(input);
                }
                prices.add(cost);
                pr_adapter.notifyDataSetChanged();
            }

        });
    }

    public void onClickDone(View v){
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}
