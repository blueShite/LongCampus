package com.example.longhengyu.longcampus.Circle.CircleDetail;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Circle.Bean.CircleHeaderBean;
import com.example.longhengyu.longcampus.Circle.Bean.CircleItemBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Adapter.CircleDetailAdapter;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailHeaderBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailItemBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Interface.CircleDetailInterface;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Presenter.CircleDetailPresenter;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class CircleDetailActivity extends BaseActivity implements CircleDetailInterface {

    @BindView(R.id.recyclerview_circleDetail)
    RecyclerView mRecyclerviewCircleDetail;
    @BindView(R.id.refreshLayout_circleDetail)
    TwinklingRefreshLayout mRefreshLayoutCircleDetail;

    private String groupId;

    private List<CircleDetailItemBean> mList = new ArrayList<>();
    private CircleDetailHeaderBean mBean;
    private CircleDetailPresenter mPresenter = new CircleDetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        ButterKnife.bind(this);
        customView();
        mPresenter.requestData(groupId,"1");
    }

    private void customView() {

        mPresenter.setContext(CircleDetailActivity.this);
        CircleHeaderBean headerBean = (CircleHeaderBean) getIntent().getSerializableExtra("circleHeaderBean");
        CircleItemBean itemBean = (CircleItemBean) getIntent().getSerializableExtra("circleItemBean");
        if (headerBean != null && !headerBean.getGroup_id().isEmpty()) {
            groupId = headerBean.getGroup_id();
        }
        if (itemBean != null && !itemBean.getGroup_id().isEmpty()) {
            groupId = itemBean.getGroup_id();
        }

        LinearLayoutManager manager = new LinearLayoutManager(CircleDetailActivity.this);
        mRecyclerviewCircleDetail.setLayoutManager(manager);
        CircleDetailAdapter adapter = new CircleDetailAdapter(mList,mBean,CircleDetailActivity.this);
        mRecyclerviewCircleDetail.setAdapter(adapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(CircleDetailActivity.this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRefreshLayoutCircleDetail.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(CircleDetailActivity.this);
        mRefreshLayoutCircleDetail.setBottomView(loadingView);
        mRefreshLayoutCircleDetail.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                mRefreshLayoutCircleDetail.finishRefreshing();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayoutCircleDetail.finishLoadmore();
                    }
                },2000);
            }
        });

    }

    @Override
    public void requestSucess(CircleDetailHeaderBean headerBean, List<CircleDetailItemBean> list) {

        mList = list;
        mList.add(0,new CircleDetailItemBean());
        mBean = headerBean;
        CircleDetailAdapter adapter = (CircleDetailAdapter)mRecyclerviewCircleDetail.getAdapter();
        adapter.reoadItem(mList,mBean);

    }
}
