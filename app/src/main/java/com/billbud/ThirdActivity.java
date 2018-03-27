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
    //Iterator<ArrayList<String>> iter;

    TextView nameView;
    Button next;
    ListView listView;

    private class ListElement {
        ListElement(String item, Double price){
            this.item = item;
            this.price = price;
            this.checked = false;
        }

        public String item;
        public Double price;
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

                // Set a listener for the whole list item
                /*newView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        // Action to do when cell is clicked
                        String s = v.getTag().toString();
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, s, duration);
                        toast.show();

                        // Intent to go to next activity passing url
                        Intent intent = new Intent(context, ReaderActivity.class);
                        intent.putExtra("url", v.getTag().toString());
                        startActivity(intent);
                    }
                });*/

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
        myAdapter = new MyAdapter(this, R.layout.single_row, aList);
        ListView myListView = (ListView) findViewById(R.id.lv);
        myListView.setAdapter(myAdapter);
        myAdapter.notifyDataSetChanged();
        rebuild_lists();
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
                //Else we go to the next page
            }
        });
    }

}
