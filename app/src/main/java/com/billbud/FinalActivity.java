package com.billbud;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.util.*;

import android.view.ViewGroup;
import android.graphics.Color;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by slapy on 3/29/2018.
 */

public class FinalActivity extends AppCompatActivity{
    AppInfo appInfo;

    //ArrayList<String> names;
    ArrayList<Double> costs;
    ArrayList<String> aList;

    private class List_Element {
        String name;
        double cost;

        List_Element(String name, double cost){
            cost = cost*100.0;
            double new_cost = Math.round(cost);
            this.cost = new_cost/100.0;

            this.name = name;
        }

        @Override
        public String toString(){
            return name + " pays: " + "$" + cost;
        }
    }

    ArrayAdapter<String> myArrayAdapter;
    ListView myListView;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        appInfo = appInfo.getInstance(this);
        aList = new ArrayList<String>();


        myArrayAdapter = new ArrayAdapter<String>(this, R.layout.total_screen, aList);

        myListView = (ListView) findViewById(R.id.split_bill);




        String temp = appInfo.sharedStringNames;
        String[] names = temp.split(",");

        temp = appInfo.sharedStringCosts;
        String[] tempCosts = temp.split(",");

        for(int i = 0; i < tempCosts.length; i++){
            double price = Double.parseDouble(tempCosts[i]);
            List_Element elem = new List_Element(names[i], price);
            aList.add(elem.toString());
        }

        myListView.setAdapter(myArrayAdapter);
        myArrayAdapter.notifyDataSetChanged();

    }

}
