package com.example.longhengyu.longcampus.PersonSubs.Collection;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Adapter.CollectionAdapter;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Bean.CollectionBean;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Interface.CollectionInterface;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Presenter.CollectionPresenter;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionActivity extends BaseActivity implements CollectionInterface {

    @BindView(R.id.collection_recycler)
    RecyclerView mCollectionRecycler;
    @BindView(R.id.collection_refresh)
    TwinklingRefreshLayout mCollectionRefresh;

    private CollectionPresenter mPresenter = new CollectionPresenter(this);
    private String page;
    private CollectionAdapter collectAdapter;
    private List<CollectionBean> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        customView();
        page = "1";
        mPresenter.requestCollection(page, LoginManage.getInstance().getLoginBean().getId());
    }

    private void customView(){

        mPresenter.setContext(CollectionActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(CollectionActivity.this);
        mCollectionRecycler.setLayoutManager(manager);
        collectAdapter = new CollectionAdapter(mList,CollectionActivity.this,this);
        mCollectionRecycler.setAdapter(collectAdapter);
        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(CollectionActivity.this);
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mCollectionRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(CollectionActivity.this);
        mCollectionRefresh.setBottomView(loadingView);
        mCollectionRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestCollection(page, LoginManage.getInstance().getLoginBean().getId());
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mPresenter.requestCollection(page, LoginManage.getInstance().getLoginBean().getId());
            }
        });
    }

    @Override
    public void requestCollectionSucess(List<CollectionBean> list,String page) {

        mCollectionRefresh.finishLoadmore();
        mCollectionRefresh.finishRefreshing();
        if(page.equals("1")){
            mList.clear();
        }
        mList.addAll(list);
        collectAdapter.notifyDataSetChanged();

    }

    @Override
    public void requestCollectionError(String error) {
        mCollectionRefresh.finishLoadmore();
        mCollectionRefresh.finishRefreshing();
    }

    @Override
    public void onClickCollectSelect() {

    }
}
