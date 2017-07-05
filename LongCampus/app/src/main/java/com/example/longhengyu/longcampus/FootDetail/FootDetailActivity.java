package com.example.longhengyu.longcampus.FootDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.FootDetail.Adapter.FootDetailAdapter;
import com.example.longhengyu.longcampus.FootDetail.Bean.FootDetailBean;
import com.example.longhengyu.longcampus.FootDetail.Bean.FootDetailItemBean;
import com.example.longhengyu.longcampus.FootDetail.Interface.FootDetailInterface;
import com.example.longhengyu.longcampus.FootDetail.Presenter.FootDetailPresenter;
import com.example.longhengyu.longcampus.FootList.ShopCartRequest.ShopCartChangeInterface;
import com.example.longhengyu.longcampus.FootList.ShopCartRequest.ShopcartRequest;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootDetailActivity extends BaseActivity implements FootDetailInterface {

    @BindView(R.id.recycle_footDetail)
    RecyclerView mRecycleFootDetail;

    private FeatureBean mBean;
    private FootDetailPresenter mPresenter = new FootDetailPresenter(this);
    private String isMyMenu;
    private String resId;
    private String flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_detail);
        ButterKnife.bind(this);
        customView();
        if(isMyMenu.equals("0")){
            mPresenter.requestDetail("/api/getDishCon.api.php",LoginManage.getInstance().getLoginBean().getId(),mBean.getMenu_id());
        }else {
            mPresenter.requestDetail("/api/getMealCont.api.php",LoginManage.getInstance().getLoginBean().getId(),mBean.getMenu_id());
        }

    }

    private void customView(){

        mPresenter.setContext(FootDetailActivity.this);
        mBean = (FeatureBean) getIntent().getSerializableExtra("featureBean");
        isMyMenu = getIntent().getStringExtra("isMyMenu");
        resId = getIntent().getStringExtra("resId");
        flag = getIntent().getStringExtra("flag");
        LinearLayoutManager manager = new LinearLayoutManager(FootDetailActivity.this);
        mRecycleFootDetail.setLayoutManager(manager);
    }

    @Override
    public void requestDetailSucess(FootDetailBean detailBean) {

        List<FootDetailItemBean> list;
        if(isMyMenu.equals("0")){
            list = mPresenter.hanbleFootDetailData(detailBean);
        }else {
            list = mPresenter.hanbleMyDetailData(detailBean);
        }
        FootDetailAdapter adapter = new FootDetailAdapter(list,detailBean,FootDetailActivity.this,this);
        mRecycleFootDetail.setAdapter(adapter);
    }

    @Override
    public void onClickAdd(final TextView numTextView) {
        final String numStr = (Integer.parseInt(numTextView.getText().toString())+1)+"";
        ShopcartRequest.requestShopCart(resId, numStr,
                mBean.getMenu_id(), flag,FootDetailActivity.this, new ShopCartChangeInterface() {
            @Override
            public void changeShopCart() {

                numTextView.setText(numStr);
            }
        });
    }

    @Override
    public void onClickRedux(final TextView numTextView) {
        final String numStr = (Integer.parseInt(numTextView.getText().toString())-1)+"";
        ShopcartRequest.requestShopCart(resId, numStr,
                mBean.getMenu_id(), flag,FootDetailActivity.this, new ShopCartChangeInterface() {
                    @Override
                    public void changeShopCart() {
                        numTextView.setText(numStr);
                    }
                });
    }
}
