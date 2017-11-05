package com.example.user.cryptocompare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void startBtn(View view) {
        Intent intent = new Intent(this, SelectCurrencies.class);
        startActivity(intent);
    }

    public void exitBtn(View view){

        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
