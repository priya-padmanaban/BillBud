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
    double tip;

    EditText tipRate;
    Button tipAway;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tipRate = (EditText) findViewById(R.id.tipAmount);
        tipAway = (Button) findViewById(R.id.submitTipButton);
        tipAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tip = Double.parseDouble(tipRate.getText().toString());
                tip = tip/100;
            }
        });
    }
}
