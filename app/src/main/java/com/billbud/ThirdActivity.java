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
 * Created by slapy on 1/20/2018.
 */

public class ThirdActivity extends AppCompatActivity {
    AppInfo appInfo;

    ArrayList<String> names;
    ArrayList<String> items;
    ArrayList<Double> prices;
    ArrayList<Double> ind_costs;
    Iterator<String> iter;

    double tax;

    int[][] shared;

    int user_count = 0;
    //Iterator<ArrayList<String>> iter;

    TextView nameView;
    Button next;
    ListView listView;

    private class ListElement {
        ListElement(String item, String price){
            this.item = item;
            this.price = price;
            this.checked = false;
        }

        public String item;
        public String price;
        public boolean checked;

        @Override
        public String toString(){
            return this.item + " $" + this.price;
        }
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
            public View getView(int position, View convertView, ViewGroup parent) {
                RelativeLayout newView;

                final ListElement elem = getItem(position);

                // Inflate a new view if necessary.
                if (convertView == null) {
                    newView = new RelativeLayout(getContext());
                    LayoutInflater vi = (LayoutInflater)
                            getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    vi.inflate(resource,  newView, true);
                } else {
                    newView = (RelativeLayout) convertView;
                }

                // Fills in the view.
                TextView titleTextView = (TextView) newView.findViewById(R.id.food);
                TextView subtitleTextView= (TextView) newView.findViewById(R.id.price);

                titleTextView.setText(elem.item);
                subtitleTextView.setText(elem.price + "");

                return newView;
            }
    }

    private MyAdapter myAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        appInfo = AppInfo.getInstance(this);
        nameView = (TextView) findViewById(R.id.nameView);
        aList = new ArrayList<ListElement>();
        next = (Button)findViewById(R.id.next);

        myAdapter = new MyAdapter(this, R.layout.single_row, aList);
        ListView myListView = (ListView) findViewById(R.id.lv);
        myListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        rebuild_lists();

        //2D array that will contain either 1s or 0s to indicate whether or not a certain user is
        //Paying for a specific item
        shared = new int[names.size()][items.size()];


        //Here we need to specify what will happen when the individual item is clicked
        //When an item is clicked, its text color will turn RED if it has not been selected,
        //And it will be flagged as an item that the person who's name appears at the top of the
        //Screen is paying for
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                int item_index = (int) id;
                TextView item_name = (TextView) view.findViewById(R.id.food);
                TextView item_price = (TextView) view.findViewById(R.id.price);

                //If the item has already been selected, deselect it. The user may have accidentally
                //Clicked it and wants to deselect the given item.
                if(aList.get(item_index).checked){
                    item_name.setTextColor(Color.GRAY);
                    item_price.setTextColor(Color.GRAY);
                    shared[user_count][item_index] = 0;
                    aList.get(item_index).checked = false;
                    Log.d("Already clicked", " Should turn black");
                    return;
                }
                shared[user_count][item_index] = 1;
                aList.get(item_index).checked = true;
                item_name.setTextColor(Color.RED);
                item_price.setTextColor(Color.RED);

            }
        });

    }

        public void rebuild_lists(){
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
                aList.add(new ListElement(items.get(k), "$" + eachElement[k])); //Add the item and its corresponding price to the list displayed by app
            }


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
            iter = names.iterator();
            iter.next();

            nameView.setText(names.get(0));

            tempString = appInfo.sharedStringTax;
            tax = Double.parseDouble(tempString);

            ind_costs = new ArrayList<Double>();


        }

    //What we need to to here is to figure out how to save the data from the clicked boxes,
    //reset the boxes, and continue to take in the next input for the next name
    public void onClickNext(View v){
        Log.d("number of names " , names.size() + "");

        //First we need to add up what the user is paying (no tip yet)
        double total = 0.0;
        for(int i = 0; i < aList.size(); i++){
            if(shared[user_count][i] == 1){
                total += prices.get(i);
            }
        }

        ind_costs.add(total + (tax/names.size()));
        Log.d("Total cost for user " + user_count, " " + (total+(tax/names.size())));

        if(iter.hasNext()) {
            //Iterate to the next name in the list, reset all colors of the items in the list
            nameView.setText(iter.next());

            ListView myListView = (ListView) findViewById(R.id.lv);

            View row;

            //Reset all of the rows back to gray
            for(int i = 0; i < myListView.getCount(); i++){
                row = myListView.getChildAt(i);
                TextView food_item = (TextView) row.findViewById(R.id.food);
                TextView item_price = (TextView) row.findViewById(R.id.price);
                food_item.setTextColor(Color.GRAY);
                item_price.setTextColor(Color.GRAY);
                aList.get(i).checked = false;
            }
            Log.d("onClickNext", " All rows reset");

            //Now we must add up the price for the individual

            user_count++;

        } else {
            
        }
        //Else we go to the next page
    }

}
