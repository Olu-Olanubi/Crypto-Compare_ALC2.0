package com.example.user.cryptocompare;

/**
 * Created by OLUMIDE OLAUBI on 11/3/2017.
 */

//This is a model class to hold selected data gotten from the json request to the cryptocompare api
class DisplayModel {
    //curr holds the specific currency notation
    private String curr;

    //btcValue and ehtValue holds the present exchange rate of curr
    private double btcValue, ethValue;

   //Constructor to initialize DisplayModel class
    DisplayModel(String currency, double btc, double eth){
        curr = currency;
        btcValue = btc;
        ethValue = eth;
    }

    //Getter methods for DisplayModel
    String getCurrency(){ return curr;}
    double getBtcValue(){return btcValue;}
    double getEthValue(){return ethValue;}
}
