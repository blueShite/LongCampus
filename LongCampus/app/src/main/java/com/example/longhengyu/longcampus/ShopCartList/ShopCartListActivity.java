package com.example.longhengyu.longcampus.ShopCartList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCartList.Interface.ShopCartListInterface;
import com.example.longhengyu.longcampus.ShopCartList.Presenter.ShopCartListPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCartListActivity extends BaseActivity implements ShopCartListInterface {

    @BindView(R.id.shopcart_list_recycle)
    RecyclerView mShopcartListRecycle;

    private ShopCartListPresenter mPresenter = new ShopCartListPresenter(this);
    private String mResId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_list);
        ButterKnife.bind(this);
        customView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestShopCartList(LoginManage.getInstance().getLoginBean().getId(),mResId);
    }

    private void customView(){

        mPresenter.setContext(ShopCartListActivity.this);
        mResId = getIntent().getStringExtra("resId");
        LinearLayoutManager layoutManager = new LinearLayoutManager(ShopCartListActivity.this);
        mShopcartListRecycle.setLayoutManager(layoutManager);

    }

    @OnClick(R.id.button_shopcart_list_submit)
    public void onViewClicked() {
    }

    @Override
    public void requestShopListSucess() {

    }
}
