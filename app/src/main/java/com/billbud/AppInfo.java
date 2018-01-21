package com.billbud;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by slapy on 1/20/2018.
 */

public class AppInfo {

    private static AppInfo instance = null;
    private static final String NAMES = "Sahil";
    private static final String ITEMS = "Joven";
    private static final String PRICES = "Markanpay";
    private static final String IND_COSTS = "Apple";

    protected AppInfo() {

    }

    // Here are some values we want to keep global
    public String sharedStringNames;
    public String sharedStringItems;
    public String sharedStringPrices;
    public String sharedStringCosts;

    private Context my_context;

    public static AppInfo getInstance(Context context) {
        if(instance == null){
            instance = new AppInfo();
            instance.my_context = context;
            SharedPreferences settings = context.getSharedPreferences(MainActivity.MYPREFS, 0);
            instance.sharedStringNames = settings.getString(NAMES, null);
            instance.sharedStringItems = settings.getString(ITEMS, null);
            instance.sharedStringPrices = settings.getString(PRICES, null);
            instance.sharedStringCosts = settings.getString(IND_COSTS,null);

        }
        return instance;
    }

    //each arraylist will be represented by a number, ie NAMES is array 1, ITEMS is 2, etc.
    public void setString(String c, int array){
        SharedPreferences settings = my_context.getSharedPreferences(MainActivity.MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        switch(array){
            case(1):
                sharedStringNames = c;
                editor.putString(NAMES, c);
                break;
            case(2):
                sharedStringItems = c;
                editor.putString(ITEMS, c);
                break;
            case(3):
                sharedStringPrices = c;
                editor.putString(PRICES, c);
                break;
            default:
                sharedStringCosts = c;
                editor.putString(IND_COSTS, c);
                break;
        }
        editor.commit();

    }

}
