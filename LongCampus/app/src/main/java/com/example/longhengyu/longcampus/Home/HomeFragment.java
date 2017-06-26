package com.example.longhengyu.longcampus.Home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.example.longhengyu.longcampus.Base.BaseFragment;
import com.example.longhengyu.longcampus.FootList.FootListActivity;
import com.example.longhengyu.longcampus.Home.Adapter.HomeAdapter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.Home.Interface.HomeAdapterInterface;
import com.example.longhengyu.longcampus.Home.Interface.HomeInterface;
import com.example.longhengyu.longcampus.Home.Presenter.HomePresenter;
import com.example.longhengyu.longcampus.LocationAbout.LongLocation;
import com.example.longhengyu.longcampus.LocationAbout.LongLocationListener;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.ShopCartActivity;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by longhengyu on 2017/4/20.
 */

public class HomeFragment extends SupportFragment implements HomeInterface,HomeAdapterInterface,LongLocationListener {

    @BindView(R.id.text_home_dingwei)
    TextView mTextHomeDingwei;
    @BindView(R.id.recyclerview_home)
    RecyclerView mRecyclerviewHome;
    @BindView(R.id.refreshLayout_home)
    TwinklingRefreshLayout mRefreshLayoutHome;

    private List<CanteenBean> mList = new ArrayList<>();;
    private HomePresenter mPresenter = new HomePresenter(this);
    private String page;
    private String locationStr = "正在定位中...";

    private LongLocation mLongLocation;


    public static HomeFragment newInstance(String info) {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);
        initView();
        mPresenter.requestHomeData(page);
        mLongLocation.startLocation();
        return view;
    }

    protected void initView() {

        customView();
        page = "1";
        mLongLocation = new LongLocation(getContext(),this);
        mTextHomeDingwei.setText(locationStr);

    }

    private void customView(){

        mPresenter.setContext(getContext());
        HomeAdapter adapter = new HomeAdapter(mList,getContext());
        adapter.setAdapterInterface(this);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerviewHome.setLayoutManager(manager);
        mRecyclerviewHome.setAdapter(adapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRefreshLayoutHome.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mRefreshLayoutHome.setBottomView(loadingView);
        mRefreshLayoutHome.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestHomeData(page);
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mPresenter.requestHomeData(page);
            }
        });
    }

    @OnClick(R.id.relative_home_dingwei)
    public void onViewClicked() {

    }

    //成功时回调的监听
    @Override
    public void requestHomeDataSucess(List<CanteenBean> list) {

        mRefreshLayoutHome.finishRefreshing();
        mRefreshLayoutHome.finishLoadmore();
        if(page.equals("1")){

            mList = list;
            mList.add(0,new CanteenBean());
            HomeAdapter adapter = (HomeAdapter) mRecyclerviewHome.getAdapter();
            adapter.reloadData(mList);
            mRefreshLayoutHome.finishRefreshing();

        }
    }

    @Override
    public void requestHomeDataError(String error) {
        mRefreshLayoutHome.finishRefreshing();
        mRefreshLayoutHome.finishLoadmore();
    }

    //点击item的监听
    @Override
    public void onClickAdapter(int posit) {

        /*Intent intent = new Intent(getActivity(), ShopCartActivity.class);
        intent.putExtra("canteenBean",mList.get(posit));
        startActivity(intent);*/
        Intent intent = new Intent(getActivity(), FootListActivity.class);
        intent.putExtra("canteenBean",mList.get(posit));
        startActivity(intent);

    }

    //定位成功时候的回调
    @Override
    public void LocationSucess(final BDLocation location) {

        if(location.getLocType()==61||location.getLocType()==161){

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    locationStr = location.getStreet();
                    mTextHomeDingwei.setText(locationStr);
                }
            });

            Toasty.success(getContext(),"定位成功"+location.getStreet()).show();
            mLongLocation.stopLocation();
        }else {

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    locationStr = "定位失败了";
                    mTextHomeDingwei.setText(locationStr);
                }
            });
            Toasty.error(getContext(),"定位失败了"+location.getLocType()).show();
        }
    }
}
