package com.example.user.cryptocompare;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * Created by OLUMIDE OLANUBI on 10/28/2017.
 */

public class SelectCurrencies extends AppCompatActivity{
    LinearLayout checkBoxContainer;
    private String[] currencies;
    private CheckBox checkBox;
    public String[] symbols;
    static String selected;
    private Button checkBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currencies_select);
        checkBoxContainer = (LinearLayout) findViewById(R.id.checkbox_container);
        currencies = getResources().getStringArray(R.array.currencies);
        symbols = getResources().getStringArray(R.array.symbols);
        for (int i = 0; i < currencies.length; i++) {
            checkBox = new CheckBox(this);
            checkBoxContainer.addView(checkBox);
            checkBox.setText(currencies[i]);
            checkBox.setId(i);
        }

        //Initializes a new button inside the layout.
        //
        checkBtn = new Button(this);
        ViewGroup.LayoutParams lparams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        checkBtn.setLayoutParams(lparams);
        checkBtn.setText("View Exchange Rates");

        //when the User clicks on the button, a new view is loaded showing the current exchange rate of all the chosen currencies.
        checkBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Code here executes on main thread after user presses button
                selected = getSelected();
               Intent intent = new Intent(getApplicationContext(), CardsDisplay.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), selected, Toast.LENGTH_LONG).show();
            }
        });
        checkBoxContainer.addView(checkBtn);
    }

    //Method to sort currencies chosen by the user to view exchange rate
    public String getSelected(){
        ArrayList<String> selections = new ArrayList();
        final int COUNT = checkBoxContainer.getChildCount();
        for (int k = 0; k < COUNT; k++) {
            View  view = checkBoxContainer.getChildAt(k);
            if (view instanceof CheckBox) {
                if(((CheckBox) view).isChecked()){
                    selections.add(symbols[ view.getId()]);
                }
            }

        }
        StringBuilder sb = new StringBuilder();
        for (String s:selections)
        {
            sb.append(s);
            sb.append(",");
        }
        if(sb.length() != 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

}
