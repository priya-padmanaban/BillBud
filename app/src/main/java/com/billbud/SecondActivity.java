package com.billbud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import java.util.*;

/**
 * Created by slapy on 1/20/2018.
 */

public class SecondActivity extends AppCompatActivity{
    AppInfo appInfo;

    EditText it_ed;
    EditText pr_ed;
    EditText tax_edit;
    ListView lv1;
    ListView lv2;

    Button it_bt;
    Button pr_bt;

    ArrayList<String> items;
    ArrayAdapter<String> it_adapter;
    ArrayList<String> prices;
    ArrayAdapter<String> pr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        appInfo = AppInfo.getInstance(this);
        it_ed = (EditText) findViewById(R.id.et_item);
        pr_ed = (EditText) findViewById(R.id.et_price);
        tax_edit = (EditText) findViewById(R.id.editTax);
        lv1 = (ListView) findViewById(R.id.lv_item);
        lv2 = (ListView) findViewById((R.id.lv_price));

        it_bt = (Button) findViewById(R.id.it_button);
        pr_bt = (Button) findViewById(R.id.pr_button);

        items = new ArrayList<String>();
        prices = new ArrayList<String>();

        it_adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                items);
        lv1.setAdapter(it_adapter);
        pr_adapter = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1,
                prices);
        lv2.setAdapter(pr_adapter);

        onItBtnClick();
        onPrBtnClick();
    }

    public void onItBtnClick(){
        it_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String input = it_ed.getText().toString();
                items.add(input);
                it_adapter.notifyDataSetChanged();
            }

            });
    }

    public void onPrBtnClick(){
        pr_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //Double cost;
                String input = pr_ed.getText().toString();
                /*if(input == null || input.isEmpty()) {
                    cost = 0.0;
                } else {
                    cost = Double.parseDouble(input);
                }*/
                if(input.charAt(0) < 48 || input.charAt(0) > 57 ){
                    Toast toast = Toast.makeText(getBaseContext(), "Please enter a decimal number (Excluding $)", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                prices.add(input);
                pr_adapter.notifyDataSetChanged();
            }

        });
    }

    public void onClickDone(View v){
        Double tax;
        String input = tax_edit.getText().toString();
        Log.d("tax input:", input);
        if(input == null || input.isEmpty()){
            tax = 0.0;
        } else {
            tax = Double.parseDouble(input);
        }

        StringBuilder sB = new StringBuilder();
        StringBuilder doubles = new StringBuilder();
        for(int i = 0; i < items.size(); i++){
            sB.append(items.get(i));
            doubles.append(prices.get(i));
            sB.append(",");
            doubles.append(",");
        }
        appInfo.setString(sB.toString(), 2);
        appInfo.setString(doubles.toString(), 3);
        appInfo.setString(input, 5);
        Log.d("after calls to app info", "WHats wrong?");
        Intent intent = new Intent(this, ThirdActivity.class);
        startActivity(intent);
    }
}
