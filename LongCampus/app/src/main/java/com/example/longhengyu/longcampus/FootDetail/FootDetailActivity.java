package com.example.longhengyu.longcampus.FootDetail;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.FootDetail.Adapter.FootDetailAdapter;
import com.example.longhengyu.longcampus.FootDetail.Bean.FootDetailItemBean;
import com.example.longhengyu.longcampus.FootDetail.Presenter.FootDetailPresenter;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FootDetailActivity extends BaseActivity {

    @BindView(R.id.recycle_footDetail)
    RecyclerView mRecycleFootDetail;

    private ShopCartBean mBean;
    private FootDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foot_detail);
        ButterKnife.bind(this);
        customView();
    }

    private void customView(){

        mBean = (ShopCartBean) getIntent().getSerializableExtra("shopCartBean");
        LinearLayoutManager manager = new LinearLayoutManager(FootDetailActivity.this);
        mRecycleFootDetail.setLayoutManager(manager);
        mPresenter = new FootDetailPresenter();
        List<FootDetailItemBean> list = mPresenter.hanbleFootDetailData(mBean);
        FootDetailAdapter adapter = new FootDetailAdapter(list,mBean,FootDetailActivity.this);
        mRecycleFootDetail.setAdapter(adapter);
    }
}
