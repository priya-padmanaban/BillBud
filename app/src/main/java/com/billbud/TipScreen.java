package com.billbud;

import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by kayla on 1/20/2018.
 */

public class TipScreen extends AppCompatActivity{
    AppInfo appInfo;

    double tip;

    double[] ind_costs;

    EditText tipRate;
    Button tipAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tip_screen);

        appInfo = appInfo.getInstance(this);

        tipRate = (EditText) findViewById(R.id.tipAmount);
        tipAway = (Button) findViewById(R.id.submitTipButton);

        String temp = appInfo.sharedStringCosts;
        String[] each_element = temp.split(",");

        ind_costs = new double[each_element.length];
        for(int i = 0; i < each_element.length; i++){
            ind_costs[i] = Double.parseDouble(each_element[i]);
        }


    }

    public void onClickTip(View v){
        tip = Double.parseDouble(tipRate.getText().toString());
        tip = tip/100.0;

        //Now we apply the tip to the adjusted costs for each user
        for(int i = 0; i < ind_costs.length; i++){
            ind_costs[i] += ind_costs[i]*tip;
        }

        StringBuilder costs = new StringBuilder();

        for(int i = 0; i < ind_costs.length; i++){
            costs.append(ind_costs[i]);
            costs.append(",");
        }

        appInfo.setString(costs.toString(), 4);
        Intent intent = new Intent(this, FinalActivity.class);
        startActivity(intent);
    }
}
