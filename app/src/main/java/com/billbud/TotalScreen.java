package com.billbud;

/**
 * Created by kayla on 1/20/2018.
 */

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.sql.Array;

public class TotalScreen {
    // Array to store the amount total for each person
    int[] totalAmt = int[5];

    AppInfo appMan;
    public TotalScreen() {
        total = AppInfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        /*
        * int total = 0;
        * for(int i = 0; i < usercount; i++){
        	for(int j = 0; j < items.size(); j++){
        		if(shared[i][j] == 1){
        			total = total + [itemArray].index(i);
        		}
        	}
        	total = total + ([tip]*total);
        	totalAmt[i] = total;
        	total = 0;
        }

         */

        // Creating the adapter for the numerical amount and taking the values from totalAmt array
        ArrayAdapter<String> totalAmtAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, totalAmt);
        // retreives the ListView in the layout
        ListView totalListView = (ListView)FindViewById(R.id.totalListView);
        // connects the ListView from the Layout to the adapter
        totalListView.setAdapter(totalAmtAdapter);

        totalListView.setAdapter(totalAmtAdapter);

        //-------------------------------------------------------------------------
        // creating the adapter for the ArrayList of people
        // ArrayAdapter<String> peopleAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item 1, [name]);

        //retreives the ListView in the Layout
        // ListView totalPeople = (ListView)FindViewById(R.id.totalPeople);

        // connects the ListView from the layout to the adapter
        // totalPeople.setAdapter(peopleAdapter);

    }
}
