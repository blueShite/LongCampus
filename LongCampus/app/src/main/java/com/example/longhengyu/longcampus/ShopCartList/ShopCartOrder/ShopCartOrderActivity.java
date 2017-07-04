package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCartOrderActivity extends BaseActivity {

    @BindView(R.id.shop_list_order_recycle)
    RecyclerView mShopListOrderRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_order);
        ButterKnife.bind(this);
    }

    private void customView(){

        LinearLayoutManager layoutManager = new LinearLayoutManager(ShopCartOrderActivity.this);
        mShopListOrderRecycle.setLayoutManager(layoutManager);

    }

    @OnClick(R.id.button_shop_list_order_submit)
    public void onViewClicked() {
    }
}
