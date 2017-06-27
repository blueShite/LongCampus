package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.SaleAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.SaleInterface;
import com.example.longhengyu.longcampus.FootList.SubFootList.Presenter.SalePresenter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;
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
 * A simple {@link Fragment} subclass.
 */
public class SaleFragment extends SupportFragment implements SaleInterface {

    @BindView(R.id.sale_fragment_recycler)
    RecyclerView mSaleFragmentRecycler;
    @BindView(R.id.sale_refresh)
    TwinklingRefreshLayout mSaleRefresh;

    private View mView;
    private SaleAdapter mAdapter;
    private List<ShopCartBean> mList = new ArrayList<>();
    private SalePresenter mPresenter = new SalePresenter(this);
    private CanteenBean mCanteenBean;

    public SaleFragment(CanteenBean canteenBean) {
        mCanteenBean = canteenBean;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_sale, container, false);
        ButterKnife.bind(this, mView);
        customView();
        mPresenter.requestList(mCanteenBean.getRes_id());
        return mView;
    }

    private void customView() {

        mPresenter.setContext(getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mSaleFragmentRecycler.setLayoutManager(layoutManager);
        mAdapter = new SaleAdapter(mList,getContext(),this);
        mSaleFragmentRecycler.setAdapter(mAdapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mSaleRefresh.setHeaderView(headerView);
        mSaleRefresh.setEnableLoadmore(false);
        mSaleRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void requestSucess(List<ShopCartBean> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickAddShopCart(int poist) {

    }
}
