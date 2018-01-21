package com.billbud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import java.util.*;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

/**
 * Created by slapy on 1/20/2018.
 */

public class ThirdActivity extends AppCompatActivity {
    AppInfo appInfo;

    ArrayList<String> names;
    ArrayList<String> items;
    ArrayList<Double> prices;
    ArrayList<Double> ind_costs;
    Iterator<String> iter;
    //Iterator<ArrayList<String>> iter;

    TextView nameView;
    Button next;


    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        appInfo = AppInfo.getInstance(this);
        nameView = (TextView)findViewById(R.id.nameView);
        next = (Button)findViewById(R.id.next);

        String tempString = appInfo.sharedStringNames;
        Log.d("names array: ", tempString);
        String[] eachElement = tempString.split(",");

        //Rebuild the names ArrayList from the shared string
        names = new ArrayList<String>();
        Log.d("eachElement", eachElement.length + "");
        nameView.setText(eachElement[0]);
        for(int i = 0; i < eachElement.length; i++){
            names.add(eachElement[i]);
            Log.d("Read names ArrayList: ", names.get(i));
        }

        //Log.d("Names.get(0)", names.get(0));
        //iter = names.iterator();
        nameView.setText(names.get(0));

        //Rebuild the items ArrayList
        tempString = appInfo.sharedStringItems;
        Log.d("Items", tempString);
        eachElement = tempString.split(",");

        items = new ArrayList<String>();
        for(int i = 0; i < eachElement.length; i++){
            items.add(eachElement[i]);
            Log.d("item element", items.get(i));
        }

        //Rebuild the prices
        tempString = appInfo.sharedStringPrices;
        Log.d("Prices", tempString);
        eachElement = tempString.split(",");

        prices = new ArrayList<Double>();
        Double d;
        for(int k = 0; k < eachElement.length; k++){
            Log.d("element", eachElement[k]);
            d = Double.parseDouble(eachElement[k]);
            prices.add(d);
        }

        ind_costs = new ArrayList<Double>();

    }

    //What we need to to here is to figure out how to save the data from the clicked boxes,
    //reset the boxes, and continue to take in the next input for the next name
    public void onClickNext(View v){
        iter = names.iterator();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(iter.hasNext())
                    nameView.setText(iter.next());
            }
        });
    }
}
