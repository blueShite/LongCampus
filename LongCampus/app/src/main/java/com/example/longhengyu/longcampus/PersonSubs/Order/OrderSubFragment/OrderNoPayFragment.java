package com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderDetail.OrderDetailActivity;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Adapter.OrderNoPayAdapter;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Bean.OrderBean;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Interface.OrderOnPayListInterface;
import com.example.longhengyu.longcampus.PersonSubs.Order.OrderSubFragment.Presenter.OrderNoPayPresenter;
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
    private OrderNoPayPresenter mPresenter = new OrderNoPayPresenter(this);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_order_nopay, container, false);
        ButterKnife.bind(this, mView);
        customView();
        page = "1";
        return mView;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.requestOrderList(LoginManage.getInstance().getLoginBean().getId(),page,"3");
    }

    private void customView(){

        mPresenter.setContext(getContext());
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
                mPresenter.requestOrderList(LoginManage.getInstance().getLoginBean().getId(),page,"3");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int indexPage = Integer.parseInt(page)+1;
                page = indexPage+"";
                mPresenter.requestOrderList(LoginManage.getInstance().getLoginBean().getId(),page,"3");
            }
        });
    }

    @Override
    public void requestOrderList(List<OrderBean> list) {
        mOrderNopayRefresh.finishRefreshing();
        mOrderNopayRefresh.finishLoadmore();
        if(page.equals("1")){
            mList.clear();
        }
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestListError(String error) {
        mOrderNopayRefresh.finishRefreshing();
        mOrderNopayRefresh.finishLoadmore();
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

    @Override
    public void onClickOrderItem(int poist) {
        Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
        startActivity(intent);
    }
}
