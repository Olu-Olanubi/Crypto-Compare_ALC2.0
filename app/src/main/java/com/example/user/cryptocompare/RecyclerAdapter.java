package com.example.user.cryptocompare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter class for the RecyclerView
 *
 * Created by OLUMIDE OLANUBI on 11/3/2017.
 */

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.viewHolder>{
    interface OnItemClickListener {
        void onItemClick(DisplayModel item);
    }

    private List<DisplayModel> sourceItemList;
    private final OnItemClickListener listener;

    //private Context context;

    RecyclerAdapter(List<DisplayModel> srcItemList, OnItemClickListener listener) {

        sourceItemList = srcItemList;
        this.listener = listener;

    }
    @Override
    public RecyclerAdapter.viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.display_cards, parent, false);
        return new viewHolder(inflatedView);

    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.viewHolder holder, int position) {
        holder.bind(sourceItemList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return sourceItemList.size();

    }

    static class viewHolder extends RecyclerView.ViewHolder  {
        //implements View.OnClickListener
        private TextView currencyLogo;
        private TextView btcValue;
        private TextView ethValue;

        viewHolder(View itemView){
            super(itemView);
            currencyLogo = (TextView) itemView.findViewById(R.id.currency_logo);
            btcValue = (TextView) itemView.findViewById(R.id.btcValue);
            ethValue = (TextView) itemView.findViewById(R.id.ethValue);

            //Edit Text Here-- itemView.setOnClickListener(this);
        }
        //
        void bind(final DisplayModel item, final OnItemClickListener listener){
            currencyLogo.setText(item.getCurrency());
            btcValue.setText((String.valueOf(item.getBtcValue())));
            ethValue.setText((String.valueOf(item.getEthValue())));
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }

}
