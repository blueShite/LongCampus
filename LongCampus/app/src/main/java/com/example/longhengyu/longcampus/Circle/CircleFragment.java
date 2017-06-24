package com.example.longhengyu.longcampus.Circle;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.Circle.Adapter.CircleAdapter;
import com.example.longhengyu.longcampus.Circle.Bean.CircleHeaderBean;
import com.example.longhengyu.longcampus.Circle.Bean.CircleItemBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.CircleDetailActivity;
import com.example.longhengyu.longcampus.Circle.Interface.CircleInterface;
import com.example.longhengyu.longcampus.Circle.Presenter.CirclePresenter;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by longhengyu on 2017/4/20.
 */

public class CircleFragment extends SupportFragment implements CircleInterface {


    @BindView(R.id.recyclerview_circle)
    RecyclerView mRecyclerviewCircle;
    @BindView(R.id.refreshLayout_circle)
    TwinklingRefreshLayout mRefreshLayoutCircle;

    CirclePresenter mPresenter = new CirclePresenter(this);

    List<CircleHeaderBean> mBannerList = new ArrayList<>();
    List<CircleItemBean> mItemList = new ArrayList<>();


    public static CircleFragment newInstance(String info) {
        Bundle args = new Bundle();
        CircleFragment fragment = new CircleFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circle, container, false);
        ButterKnife.bind(this, view);
        customView();
        mPresenter.requestBanner();
        return view;
    }

    private void customView(){

        mPresenter.setContext(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerviewCircle.setLayoutManager(manager);
        CircleAdapter circleAdapter = new CircleAdapter(mBannerList,mItemList,getContext(),this);
        mRecyclerviewCircle.setAdapter(circleAdapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRefreshLayoutCircle.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mRefreshLayoutCircle.setBottomView(loadingView);
        mRefreshLayoutCircle.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                mPresenter.requestBanner();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayoutCircle.finishLoadmore();
                    }
                },2000);
            }
        });
    }

    @Override
    public void requestSucess(List<CircleHeaderBean> bannerList, List<CircleItemBean> itemList) {

        mRefreshLayoutCircle.finishRefreshing();
        mItemList = itemList;
        mItemList.add(0,new CircleItemBean());
        mBannerList = bannerList;
        CircleAdapter adapter = (CircleAdapter) mRecyclerviewCircle.getAdapter();
        adapter.reloadItem(bannerList, itemList);

    }

    @Override
    public void onClickHeader(int poist) {

        Intent intent = new Intent(getActivity(), CircleDetailActivity.class);
        intent.putExtra("circleHeaderBean",mBannerList.get(poist));
        startActivity(intent);

    }

    @Override
    public void onClickItem(int poist) {

        Intent intent = new Intent(getActivity(),CircleDetailActivity.class);
        intent.putExtra("circleItemBean",mItemList.get(poist));
        startActivity(intent);

    }
}
