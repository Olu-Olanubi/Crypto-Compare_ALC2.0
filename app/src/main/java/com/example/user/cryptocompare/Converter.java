package com.example.user.cryptocompare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**Java class to perform conversions from base currency to cryptocurrency
 *
 *
 * Created by USER on 11/5/2017.
 */


public class Converter extends AppCompatActivity {

 static String symbol;
    static double btcRate;
    static double ethRate;
    double amount, btcAmount, ethAmount;
    TextView currency_symbol, btc_amount, eth_amount;
    EditText convert_amount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);
        currency_symbol = (TextView) findViewById(R.id.symbol);
        btc_amount = (TextView) findViewById(R.id.btcResult);
        eth_amount = (TextView) findViewById(R.id.ethResult);
        convert_amount = (EditText) findViewById(R.id.amount);
        convert_amount.setSelectAllOnFocus(true);
        currency_symbol.setText(symbol);

    }

    public void convert(View v){
        amount = Double.parseDouble(convert_amount.getText().toString());
        btcAmount = amount/btcRate;
        ethAmount = amount/ethRate;
        btc_amount.setText(String.format("%.4f", btcAmount));
        eth_amount.setText(String.format("%.4f",ethAmount));
    }

}
