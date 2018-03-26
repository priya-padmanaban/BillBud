package com.billbud;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import java.util.*;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Button;
import android.content.Intent;
import android.widget.RelativeLayout;
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
    ListView listView;

    private class ListElement {
        ListElement(String item, boolean checked){
            this.item = item;
            this.checked = checked;
        }

        public String item;
        public String price;
        public boolean checked;
    }

    private ArrayList<ListElement> aList;

    private class MyAdapter extends ArrayAdapter<ListElement> {
        int resource;
        Context context;

        public MyAdapter(Context _context, int _resource, List<ListElement> things){
            super(_context, _resource, things);
            resource = _resource;
            context = _context;
        }

            //Nelson
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            RelativeLayout newView;

            final ListElement item = getItem(position);

            //Inflate view
            if(convertView == null) {
                newView = new RelativeLayout(getContext());
                LayoutInflater vi = (LayoutInflater)
                        getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                vi.inflate(resource, newView, true);
            } else {
                newView = (RelativeLayout) convertView;
            }

            TextView titleTextView = (TextView) newView.findViewById(R.id.textView1);
            CheckBox checkBox = (CheckBox) newView.findViewById(R.id.checkBox1);
            titleTextView.setText(item.item);
            if(checkBox.isChecked()){
                checkBox.setChecked(false);
            }

            return newView;
        }
    }

    private MyAdapter myAdapter;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        appInfo = AppInfo.getInstance(this);
        nameView = (TextView)findViewById(R.id.nameView);
        aList = new ArrayList<ListElement>();

        //Rebuild the items ArrayList
        String tempString = appInfo.sharedStringItems;
        Log.d("Items", tempString);
        String[] eachElement = tempString.split(",");

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

        next = (Button)findViewById(R.id.next);
        listView = (ListView)findViewById(R.id.lv);
        myAdapter = new MyAdapter(this, R.layout.single_row, aList);

        tempString = appInfo.sharedStringNames;
        Log.d("names array: ", tempString);
        eachElement = tempString.split(",");

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
