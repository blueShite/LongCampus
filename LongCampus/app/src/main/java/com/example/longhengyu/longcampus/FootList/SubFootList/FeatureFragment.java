package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.FeatureAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.RecommendAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.SaleAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.FeatureInterface;
import com.example.longhengyu.longcampus.FootList.SubFootList.Presenter.FeaturePresenter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
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
 * A simple {@link Fragment} subclass.
 */
public class FeatureFragment extends SupportFragment implements FeatureInterface {


    @BindView(R.id.feature_fragment_recycler)
    RecyclerView mFeatureFragmentRecycler;
    @BindView(R.id.feature_refresh)
    TwinklingRefreshLayout mFeatureRefresh;

    private View mView;
    private FeaturePresenter mPresenter = new FeaturePresenter(this);
    private String page;
    private CanteenBean mCanteenBean;
    private List<FeatureBean> mList = new ArrayList<>();
    private FeatureAdapter mAdapter;

    public FeatureFragment(CanteenBean canteenBean) {
        // Required empty public constructor
        mCanteenBean = canteenBean;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_feature, container, false);
        ButterKnife.bind(this, mView);
        customView();
        page = "1";
        mPresenter.requestList(page,mCanteenBean.getRes_id());
        return mView;
    }

    private void customView() {

        mPresenter.setContext(getContext());

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mFeatureFragmentRecycler.setLayoutManager(manager);

        mAdapter = new FeatureAdapter(mList,getContext(),this);
        mFeatureFragmentRecycler.setAdapter(mAdapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mFeatureRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mFeatureRefresh.setBottomView(loadingView);
        mFeatureRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestList(page,mCanteenBean.getRes_id());
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mPresenter.requestList(page,mCanteenBean.getRes_id());
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void requestSucess(List<FeatureBean> list) {
        mFeatureRefresh.finishLoadmore();
        mFeatureRefresh.finishRefreshing();
        if(page.equals("1")){
            mList.clear();
        }
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestError(String error) {
        mFeatureRefresh.finishLoadmore();
        mFeatureRefresh.finishRefreshing();
    }
}
