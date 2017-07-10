package com.example.longhengyu.longcampus.PersonSubs.Order.OrderDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderDetail.Adapter.OrderDetailAdapter;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderDetail.Bean.OrderDetailBean;
import com.example.longhengyu.longcampus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.order_detail_recycle)
    RecyclerView mOrderDetailRecycle;

    private OrderDetailAdapter mAdapter;
    private List<OrderDetailBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ButterKnife.bind(this);
        customView();
    }

    private void customView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(OrderDetailActivity.this);
        mOrderDetailRecycle.setLayoutManager(layoutManager);
        mAdapter = new OrderDetailAdapter(mList,OrderDetailActivity.this);
        mOrderDetailRecycle.setAdapter(mAdapter);

    }
}
