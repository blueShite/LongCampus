package com.example.longhengyu.longcampus.PersonSubs.Integral;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.Integral.Adapter.IntegralAdapter;
import com.example.longhengyu.longcampus.PersonSubs.Integral.Bean.IntegralBean;
import com.example.longhengyu.longcampus.PersonSubs.Integral.IntegralExchange.IntegralExchangeActivity;
import com.example.longhengyu.longcampus.PersonSubs.Integral.Interface.IntegralInterface;
import com.example.longhengyu.longcampus.PersonSubs.Integral.Presenter.IntegralPresenter;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntegralActivity extends BaseActivity implements IntegralInterface {

    @BindView(R.id.text_integral_exchange)
    TextView mTextIntegralExchange;
    @BindView(R.id.integral_recycler)
    RecyclerView mIntegralRecycler;
    @BindView(R.id.integral_refresh)
    TwinklingRefreshLayout mIntegralRefresh;

    private IntegralPresenter mPresenter = new IntegralPresenter(this);
    private String page;
    private IntegralAdapter integralAdapter;
    private List<IntegralBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        ButterKnife.bind(this);
        customView();
        page = "1";
        mPresenter.requestIntegralData(LoginManage.getInstance().getLoginBean().getId(),page);

    }

    private void customView(){

        mPresenter.setContext(IntegralActivity.this);
        mTextIntegralExchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(IntegralActivity.this, IntegralExchangeActivity.class);
                intent.putExtra("integral",getIntent().getStringExtra("integral"));
                startActivity(intent);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(IntegralActivity.this);
        mIntegralRecycler.setLayoutManager(manager);
        integralAdapter = new IntegralAdapter(mList,getIntent().getStringExtra("integral"),IntegralActivity.this);
        mIntegralRecycler.setAdapter(integralAdapter);
        SinaRefreshView headerView = new SinaRefreshView(IntegralActivity.this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mIntegralRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(IntegralActivity.this);
        mIntegralRefresh.setBottomView(loadingView);
        mIntegralRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestIntegralData(LoginManage.getInstance().getLoginBean().getId(),page);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mPresenter.requestIntegralData(LoginManage.getInstance().getLoginBean().getId(),page);
            }
        });
    }

    @Override
    public void requestIntegralSucess(List<IntegralBean> list) {
        mIntegralRefresh.finishLoadmore();
        mIntegralRefresh.finishRefreshing();
        if(page.equals("1")){
            mList.clear();
        }
        mList.addAll(list);
        integralAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestImtegralError(String error) {
        mIntegralRefresh.finishLoadmore();
        mIntegralRefresh.finishRefreshing();
    }
}
