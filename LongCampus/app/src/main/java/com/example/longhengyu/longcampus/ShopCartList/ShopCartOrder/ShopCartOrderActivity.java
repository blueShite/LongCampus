package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.CustomView.Address_AlertDialog;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.Address.AddAddressActivity;
import com.example.longhengyu.longcampus.PersonSubs.Address.AddressListActivity;
import com.example.longhengyu.longcampus.PersonSubs.Address.Bean.AddressBean;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.Bean.CouponBean;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.CouponActivity;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.SetPersonActivity;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartPriceBean;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Adapter.ShopCartOrderAdapter;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Bean.ShopCartOrderFootBean;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Event.CouponShopCartOrderEvent;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Interface.ShopCartOrderInterface;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Presenter.ShopCartOrderPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ShopCartOrderActivity extends BaseActivity implements ShopCartOrderInterface {

    @BindView(R.id.shop_list_order_recycle)
    RecyclerView mShopListOrderRecycle;

    private List<ShopCartItemBean> mList = new ArrayList<>();
    private ShopCartOrderAdapter mAdapter;
    private ShopCartOrderPresenter mPresenter = new ShopCartOrderPresenter(this);
    private ShopCartOrderFootBean mFootBean;
    private ShopCartPriceBean mPriceBean;
    private List<AddressBean> mAddressList;
    private AddressBean selectAddressBean;
    private String paramShopId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_order);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        customView();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onMessageEvent(CouponShopCartOrderEvent event) {
        CouponBean bean = event.mCouponBean;
        mFootBean.setCouponSub(bean.getTitle());
        mFootBean.setCouponId(bean.getCpid());
        mAdapter.reloadFootView(mFootBean);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestAddressList(LoginManage.getInstance().getLoginBean().getId());
    }

    private void customView(){

        mPresenter.setContext(ShopCartOrderActivity.this);
        List<ShopCartItemBean> list = (List<ShopCartItemBean>) getIntent().getSerializableExtra("shopList");
        mPriceBean = (ShopCartPriceBean) getIntent().getSerializableExtra("priceBean");
        paramShopId = getIntent().getStringExtra("shopId");
        mList.addAll(mPresenter.handleList(list));
        mFootBean = new ShopCartOrderFootBean();
        mFootBean.setCouponSub("请选择优惠券!");
        mFootBean.setGiveType(2);
        mFootBean.setTime("请选择用餐时间");
        mFootBean.setShopId(paramShopId);
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
        String remark = "";
        ShopCartItemBean itemBean;
        for (int i=1;i<mList.size();i++){
            ShopCartItemBean bean = mList.get(i);
            if(i==1){
                if(bean.getRemark()==null||bean.getRemark().length()<1){
                    remark = "";
                }else {
                    remark = bean.getRemark();
                }
            }else if(i>1&&i!=mList.size()-1){
                if(bean.getItemType().equals("0")){
                    remark = remark+"&";
                }else {
                    itemBean = mList.get(i-1);
                    if(itemBean.getId()==null){
                        remark = remark+bean.getRemark();
                    }else{
                        if(bean.getId().equals(itemBean.getId())){
                            if(bean.getRemark()==null||bean.getRemark().length()<1){
                                remark = remark+",";
                            }else {
                                remark = remark+","+bean.getRemark();
                            }

                        }else {
                            if(bean.getRemark()==null||bean.getRemark().length()<1){
                                remark = remark+"%";
                            }else {
                                remark = remark+"%"+bean.getRemark();
                            }
                        }
                    }
                }
            }else {
                itemBean = mList.get(i-1);
                if(itemBean.getItemType().equals("0")){
                    if(bean.getRemark()!=null&&bean.getRemark().length()>0){
                        remark = remark+bean.getRemark();
                    }else {

                    }
                }else {
                    if(itemBean.getId().equals(bean.getId())){

                        if(bean.getRemark()!=null&&bean.getRemark().length()>0){
                            remark = remark+","+bean.getRemark();
                        }else {
                            remark = remark+",";
                        }
                    }else {
                        if(bean.getRemark()!=null&&bean.getRemark().length()>0){
                            remark = remark+"%"+bean.getRemark();
                        }else {
                            remark = remark+"%";
                        }
                    }
                }

            }
        }
        remark = remark.replace("null","");
        Toasty.success(ShopCartOrderActivity.this,remark).show();
        Log.e("蛋疼的拼接",remark);
    }

    @OnClick(R.id.button_shop_list_order_submit)
    public void onViewClicked() {

        hanbleParams();

    }


    @Override
    public void requestAddressList(List<AddressBean> list) {
        mAddressList = list;
    }

    @Override
    public void onClickCoupon() {
        Intent couponIntent = new Intent(ShopCartOrderActivity.this, CouponActivity.class);
        couponIntent.putExtra("isSelect","1");
        startActivity(couponIntent);
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
            new Address_AlertDialog(ShopCartOrderActivity.this,mAddressList,selectAddressBean)
                    .builder()
                    .setOnClickSubmitBtn(new Address_AlertDialog.OnClickAddressAlertInterface() {
                        @Override
                        public void selectAddressItem(AddressBean bean) {
                            selectAddressBean = bean;
                            mFootBean.setAddressId(selectAddressBean.getId());
                        }
                    })
                    .setCanCelBtn(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                        }
                    })
                    .setSetingBtn(new Address_AlertDialog.OnClickAddressAlertInterface() {
                        @Override
                        public void selectAddressItem(AddressBean bean) {
                            Intent intent = new Intent(ShopCartOrderActivity.this,AddAddressActivity.class);
                            intent.putExtra("isSeting","1");
                            intent.putExtra("AddressBean",bean);
                            startActivity(intent);
                        }
                    }).show();
        }
    }

    @Override
    public void itemEditText(int poist, String editText) {
        ShopCartItemBean bean = mList.get(poist);
        bean.setRemark(editText);
        Log.e("tag2","-------"+poist+"-------"+editText);
    }
}
