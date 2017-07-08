package com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Adapter.OrderNoPayAdapter;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Bean.OrderBean;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Interface.OrderOnPayListInterface;
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
 * Created by longhengyu on 2017/7/8.
 */

public class OrderNoPayFragment extends SupportFragment implements OrderOnPayListInterface {

    @BindView(R.id.order_nopay_recycler)
    RecyclerView mOrderNopayRecycler;
    @BindView(R.id.order_nopay_refresh)
    TwinklingRefreshLayout mOrderNopayRefresh;

    private View mView;
    private String page;
    private OrderNoPayAdapter mAdapter;
    private List<OrderBean> mList = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_order_nopay, container, false);
        ButterKnife.bind(this, mView);
        customView();
        page = "1";
        return mView;
    }

    private void customView(){

        OrderBean orderBean = new OrderBean();
        orderBean.setShowComm(true);
        OrderBean orderBean1 = new OrderBean();
        orderBean1.setShowComm(false);
        OrderBean orderBean2 = new OrderBean();
        orderBean2.setShowComm(true);
        OrderBean orderBean3 = new OrderBean();
        orderBean3.setShowComm(false);
        mList.add(orderBean);
        mList.add(orderBean1);
        mList.add(orderBean2);
        mList.add(orderBean3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mOrderNopayRecycler.setLayoutManager(layoutManager);
        mAdapter = new OrderNoPayAdapter(mList,this,getContext(),0);
        mOrderNopayRecycler.setAdapter(mAdapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mOrderNopayRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mOrderNopayRefresh.setBottomView(loadingView);
        mOrderNopayRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page="1";
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int indexPage = Integer.parseInt(page)+1;
                page = indexPage+"";
            }
        });
    }

    @Override
    public void onClickShowComm(int poist) {
        mList.get(poist).setShowComm(true);
        mAdapter.notifyItemChanged(poist);
    }

    @Override
    public void onClickHideComm(int poist) {
        mList.get(poist).setShowComm(false);
        mAdapter.notifyItemChanged(poist);
    }
}
