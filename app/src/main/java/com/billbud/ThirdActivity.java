package com.billbud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import java.util.*;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;

/**
 * Created by slapy on 1/20/2018.
 */

public class ThirdActivity extends AppCompatActivity {
    AppInfo appInfo;

    ArrayList<String> names;
    ArrayList<String> items;
    ArrayList<Double> prices;
    ArrayList<Double> ind_costs;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        appInfo = AppInfo.getInstance(this);

    }

    protected void onResume(){
        super.onResume();
        String tempString = appInfo.sharedStringNames;
        String[] eachElement = tempString.split(",");
        //Rebuild the names ArrayList from the shared string
        names = new ArrayList<String>();
        for(int i = 0; i < eachElement.length; i++){
            names.add(eachElement[i]);
        }

        //Rebuild the items ArrayList
        tempString = appInfo.sharedStringItems;
        eachElement = tempString.split(",");
        items = new ArrayList<String>();
        for(int i = 0; i < eachElement.length; i++){
            items.add(eachElement[i]);
        }

        //Rebuild the prices
        tempString = appInfo.sharedStringPrices;
        eachElement = tempString.split(",");
        prices = new ArrayList<Double>();
        Double d;
        for(int k = 0; k < eachElement.length; k++){
            d = Double.parseDouble(eachElement[k]);
            prices.add(d);
        }

        ind_costs = new ArrayList<Double>();

    }
}
