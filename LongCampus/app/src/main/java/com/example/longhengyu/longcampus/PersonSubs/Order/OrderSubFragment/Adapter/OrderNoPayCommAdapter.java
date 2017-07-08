package com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.longhengyu.longcampus.R;

/**
 * Created by longhengyu on 2017/7/8.
 */

public class OrderNoPayCommAdapter extends RecyclerView.Adapter<OrderNoPayCommAdapter.ViewHolder> {


    @Override
    public OrderNoPayCommAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_nopay_comm,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderNoPayCommAdapter.ViewHolder holder, int position) {
        holder.itemText.setText("呵呵呵");
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemText;

        public ViewHolder(View itemView) {
            super(itemView);
            itemText = (TextView) itemView.findViewById(R.id.text_order_nopay_comm_text);
        }
    }
}
