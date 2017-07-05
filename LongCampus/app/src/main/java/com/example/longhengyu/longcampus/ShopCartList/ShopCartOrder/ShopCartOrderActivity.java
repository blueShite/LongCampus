package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TimePicker;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.PersonSubs.Address.AddressListActivity;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.SetPersonActivity;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartPriceBean;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Adapter.ShopCartOrderAdapter;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Bean.ShopCartOrderFootBean;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Interface.ShopCartOrderInterface;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Presenter.ShopCartOrderPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopCartOrderActivity extends BaseActivity implements ShopCartOrderInterface {

    @BindView(R.id.shop_list_order_recycle)
    RecyclerView mShopListOrderRecycle;

    private List<ShopCartItemBean> mList = new ArrayList<>();
    private ShopCartOrderAdapter mAdapter;
    private ShopCartOrderPresenter mPresenter = new ShopCartOrderPresenter(this);
    private ShopCartOrderFootBean mFootBean;
    private ShopCartPriceBean mPriceBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_order);
        ButterKnife.bind(this);
        customView();
    }

    private void customView(){

        mPresenter.setContext(ShopCartOrderActivity.this);
        List<ShopCartItemBean> list = (List<ShopCartItemBean>) getIntent().getSerializableExtra("shopList");
        mPriceBean = (ShopCartPriceBean) getIntent().getSerializableExtra("priceBean");
        mList.addAll(mPresenter.handleList(list));
        mFootBean = new ShopCartOrderFootBean();
        mFootBean.setCouponSub("请选择优惠券!");
        mFootBean.setGiveType(2);
        mFootBean.setTime("请选择用餐时间");
        mFootBean.setTotalPrice(mPriceBean.getTotal()+"");
        mFootBean.setPackPrice(mPriceBean.getPack()+"");
        mFootBean.setPayPrice((mPriceBean.getPack()+mPriceBean.getTotal())+"");

        LinearLayoutManager layoutManager = new LinearLayoutManager(ShopCartOrderActivity.this);
        mShopListOrderRecycle.setLayoutManager(layoutManager);
        mAdapter = new ShopCartOrderAdapter(mList,ShopCartOrderActivity.this,this);
        mShopListOrderRecycle.setAdapter(mAdapter);
        mAdapter.reloadFootView(mFootBean);

    }

    private void hanbleParams(){


    }

    @OnClick(R.id.button_shop_list_order_submit)
    public void onViewClicked() {
    }


    @Override
    public void onClickCoupon() {

    }

    @Override
    public void onClickTime() {
        new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker picker, int i, int i1) {
                String dateString = i+":"+i1;
                mFootBean.setTime(dateString);
                mAdapter.reloadFootView(mFootBean);
            }
        }, 12, 0, true).show();
    }

    @Override
    public void onClickGiveType(int type) {
        mFootBean.setGiveType(type);
        mAdapter.reloadFootView(mFootBean);
        if(type==1){
            Intent intent = new Intent(ShopCartOrderActivity.this, AddressListActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void itemEditText(int poist, String editText) {
        ShopCartItemBean bean = mList.get(poist);
        bean.setRemark(editText);
    }
}
