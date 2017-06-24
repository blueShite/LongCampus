package com.example.longhengyu.longcampus.Circle.CircleDetail;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

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
import butterknife.OnClick;

public class CircleDetailActivity extends BaseActivity implements CircleDetailInterface {

    @BindView(R.id.recyclerview_circleDetail)
    RecyclerView mRecyclerviewCircleDetail;
    @BindView(R.id.refreshLayout_circleDetail)
    TwinklingRefreshLayout mRefreshLayoutCircleDetail;
    @BindView(R.id.edit_circle_detail)
    EditText mEditCircleDetail;

    private String groupId;
    private String page;

    private List<CircleDetailItemBean> mList = new ArrayList<>();
    private CircleDetailHeaderBean mBean;
    private CircleDetailPresenter mPresenter = new CircleDetailPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_detail);
        ButterKnife.bind(this);
        customView();
        page = "1";
        mPresenter.requestData(groupId, page);
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
        CircleDetailAdapter adapter = new CircleDetailAdapter(mList, mBean, CircleDetailActivity.this);
        mRecyclerviewCircleDetail.setAdapter(adapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(CircleDetailActivity.this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRefreshLayoutCircleDetail.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(CircleDetailActivity.this);
        mRefreshLayoutCircleDetail.setBottomView(loadingView);
        mRefreshLayoutCircleDetail.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestData(groupId, page);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mPresenter.requestData(groupId, page);
            }
        });

    }

    @OnClick(R.id.text_circle_detail_Submit)
    public void onViewClicked() {
    }

    @Override
    public void requestHeaderData(CircleDetailHeaderBean headerBean) {

        CircleDetailAdapter adapter = (CircleDetailAdapter) mRecyclerviewCircleDetail.getAdapter();
        adapter.reloadHeader(headerBean);
    }

    @Override
    public void requestSucess(List<CircleDetailItemBean> list) {

        if(page.equals("1")){
            mList.clear();
            mList.add(0, new CircleDetailItemBean());
        }
        mList.addAll(list);
        CircleDetailAdapter adapter = (CircleDetailAdapter) mRecyclerviewCircleDetail.getAdapter();
        adapter.reoadItem(mList);
        mPresenter.requestHeaderData(groupId);

    }

    @Override
    public void requestError(String error) {
        mList.clear();
        mList.add(0, new CircleDetailItemBean());
        mPresenter.requestHeaderData(groupId);
    }


}
