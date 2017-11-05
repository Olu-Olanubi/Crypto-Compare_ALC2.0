package com.example.user.cryptocompare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.cryptocompare.SelectCurrencies.selected;


/**Java class to display data and implement RecyclerView
 *
 * Created by OLUMIDE OLANUBI on 10/29/2017.
 */

public class CardsDisplay extends AppCompatActivity {

    private RecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;
    List<DisplayModel> sourceItemList;
    private ProgressBar progressBar;
    LinearLayoutManager mLinearLayoutManager;

    String URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=" + selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_display);

        //Initialize variables
        sourceItemList = new ArrayList<>();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        sendRequest();
    }

    //Java method to make JSONRequest to cryptocompare api and fetch and display the relevant data.
    public void sendRequest() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        //Hide ProgressBar after load completion
                        progressBar.setVisibility(View.INVISIBLE);

                        // display response

                        try {

                            // get the Json object of btc and eth from the response
                            JSONObject getBTC = response.getJSONObject("BTC");
                            JSONObject getETH = response.getJSONObject("ETH");

                            //get JSONArray of currency symbols using names() method of JSONObject
                            JSONArray currencies= getBTC.names();

                            //Now loop through all the JSONobjects in the items array and extract specific details
                            //in each JSONObject
                            for (int i = 0; i < currencies.length(); i++) {
                                DisplayModel model = new DisplayModel(currencies.getString(i), getBTC.getDouble(currencies.getString(i)), getETH.getDouble(currencies.getString(i)));

                                //create a SourceItem object and give it the values from the JSONObject
                                // SourceItem sourceItem = new SourceItem(details.getString("login"), details.getString("avatar_url"), details.getString("url"));
                                //add model to sourceItemList
                                sourceItemList.add(model);

                                //create a custom recyclerview object
                                mAdapter = new RecyclerAdapter(sourceItemList, new RecyclerAdapter.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(DisplayModel item) {
                                        Converter.symbol = item.getCurrency();
                                        Converter.btcRate = item.getBtcValue();
                                        Converter.ethRate = item.getEthValue();

                                        Intent convert = new Intent(getApplicationContext(), Converter.class);
                                        startActivity(convert);
                                    }
                                });
                                // Add mAdapter to RecyclerView
                                mRecyclerView.setAdapter(mAdapter);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //If error occurs, display error in toast
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        //progressBar.setVisibility(View.INVISIBLE);
                    }
                }
        );

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(getRequest);
    }

}
