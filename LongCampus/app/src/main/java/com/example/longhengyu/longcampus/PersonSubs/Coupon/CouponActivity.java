package com.example.longhengyu.longcampus.PersonSubs.Coupon;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.Adapter.CouponAdapter;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.Bean.CouponBean;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.Interface.CouponInterface;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.Presenter.CouponPresenter;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CouponActivity extends BaseActivity implements CouponInterface {

    @BindView(R.id.coupon_recycle)
    RecyclerView mCouponRecycle;
    @BindView(R.id.coupon_refresh)
    TwinklingRefreshLayout mCouponRefresh;
    @BindView(R.id.text_coupon_receive)
    TextView mTextCouponReceive;

    private CouponPresenter mPresenter = new CouponPresenter(this);
    private List<CouponBean> mList = new ArrayList<>();
    private CouponAdapter mAdapter;
    private String page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        customView();
        page = "1";

    }

    @Override
    protected void onResume() {
        super.onResume();
        page = "1";
        mPresenter.requestList(LoginManage.getInstance().getLoginBean().getId(), page, "1");
    }

    private void customView() {

        mPresenter.setContext(CouponActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(CouponActivity.this);
        mCouponRecycle.setLayoutManager(layoutManager);
        mAdapter = new CouponAdapter(mList, CouponActivity.this, this);
        mCouponRecycle.setAdapter(mAdapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(CouponActivity.this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mCouponRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(CouponActivity.this);
        mCouponRefresh.setBottomView(loadingView);
        mCouponRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestList(LoginManage.getInstance().getLoginBean().getId(), page, "1");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page) + 1;
                page = pageIndex + "";
                mPresenter.requestList(LoginManage.getInstance().getLoginBean().getId(), page, "1");
            }
        });

        mTextCouponReceive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CouponActivity.this,CouponReceiveActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.text_coupon_overdue)
    public void onViewClicked() {
        Intent intent = new Intent(CouponActivity.this, CouponOverdueActivity.class);
        startActivity(intent);
    }


    @Override
    public void requestCouponList(List<CouponBean> list) {

        mCouponRefresh.finishLoadmore();
        mCouponRefresh.finishRefreshing();
        if (page.equals("1")) {
            mList.clear();
        }
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestCouponError(String error) {
        mCouponRefresh.finishLoadmore();
        mCouponRefresh.finishRefreshing();
    }

    @Override
    public void onClickCoupon(int poist) {

    }

    @Override
    public void requestReceiveSucess(int poist) {

    }


}
