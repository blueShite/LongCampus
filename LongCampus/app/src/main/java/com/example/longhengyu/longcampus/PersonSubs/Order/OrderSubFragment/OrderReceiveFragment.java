package com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Adapter.OrderReceiveAdapter;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by longhengyu on 2017/7/8.
 */

public class OrderReceiveFragment extends SupportFragment {

    @BindView(R.id.order_receive_recycle)
    RecyclerView mOrderReceiveRecycle;
    @BindView(R.id.order_receive_refresh)
    TwinklingRefreshLayout mOrderReceiveRefresh;

    private View mView;
    private OrderReceiveAdapter mAdapter;
    private String page;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_order_receive, container, false);
        ButterKnife.bind(this, mView);
        customView();
        page = "1";
        return mView;
    }

    private void customView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mOrderReceiveRecycle.setLayoutManager(layoutManager);
        mAdapter = new OrderReceiveAdapter();
        mOrderReceiveRecycle.setAdapter(mAdapter);
        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mOrderReceiveRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mOrderReceiveRefresh.setBottomView(loadingView);
        mOrderReceiveRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
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

}
