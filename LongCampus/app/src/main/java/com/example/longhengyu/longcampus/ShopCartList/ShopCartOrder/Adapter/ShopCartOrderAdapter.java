package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/7/4.
 */

public class ShopCartOrderAdapter extends RecyclerView.Adapter<ShopCartOrderAdapter.ViewHolder> {

    private List<ShopCartItemBean> mList;


    @Override
    public ShopCartOrderAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ShopCartOrderAdapter.ViewHolder holder, int position) {

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
