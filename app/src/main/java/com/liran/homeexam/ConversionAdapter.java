package com.liran.homeexam;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Liran on 15/12/2017.
 */

public class ConversionAdapter extends RecyclerView.Adapter<ConversionAdapter.MyViewHolder> {
    private List<Conversion> conversionList;

    public ConversionAdapter(List<Conversion> conversionList) {
        this.conversionList = conversionList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView currency, content, revenue;
        public MyViewHolder(View view){
            super(view);
            currency = view.findViewById(R.id.currency);
            revenue = view.findViewById(R.id.revenue);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.purchase_list_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ConversionAdapter.MyViewHolder holder, int position) {
        Conversion conversion = conversionList.get(position);
        holder.revenue.setText(conversion.getRevenue());
        holder.currency.setText(conversion.getCurrency());
    }

    @Override
    public int getItemCount() {
        return conversionList.size();
    }
}
