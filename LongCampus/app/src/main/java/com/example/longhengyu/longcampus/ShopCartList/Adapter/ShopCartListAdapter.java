package com.example.longhengyu.longcampus.ShopCartList.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by longhengyu on 2017/7/3.
 */

public class ShopCartListAdapter extends RecyclerView.Adapter<ShopCartListAdapter.ViewHolder> {

    @Override
    public int getItemViewType(int position) {

        return 1;
    }

    @Override
    public ShopCartListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ShopCartListAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
