package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.RecommendAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.RecommendInterface;
import com.example.longhengyu.longcampus.FootList.SubFootList.Presenter.RecommendPresenter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;
import com.example.longhengyu.longcampus.ShopCart.ShopCartActivity;
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
public class RecommendFragment extends SupportFragment implements RecommendInterface {

    @BindView(R.id.recommend_fragment_recycler)
    RecyclerView mRecommendFragmentRecycler;
    @BindView(R.id.recommend_refresh)
    TwinklingRefreshLayout mRecommendRefresh;

    private View mView;
    private List<ShopCartBean> mList = new ArrayList<>();
    private RecommendPresenter mPresenter = new RecommendPresenter(this);
    private String page;
    private CanteenBean mCanteenBean;
    private RecommendAdapter mAdapter;

    public RecommendFragment(CanteenBean canteenBean) {
        mCanteenBean = canteenBean;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recommend, container, false);
        ButterKnife.bind(this, mView);
        customView();
        page = "1";
        mPresenter.requestList(page,mCanteenBean.getRes_id(),"1");
        return mView;
    }

    private void customView() {

        mPresenter.setContext(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecommendFragmentRecycler.setLayoutManager(manager);

        mAdapter = new RecommendAdapter(mList,getContext(),this);
        mRecommendFragmentRecycler.setAdapter(mAdapter);

        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRecommendRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mRecommendRefresh.setBottomView(loadingView);
        mRecommendRefresh.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestList(page,mCanteenBean.getRes_id(),"1");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page)+1;
                page = pageIndex+"";
                mPresenter.requestList(page,mCanteenBean.getRes_id(),"1");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void requestSucess(List<ShopCartBean> list) {

        mRecommendRefresh.finishLoadmore();
        mRecommendRefresh.finishRefreshing();
        if(page.equals("1")){
            mList.clear();
        }
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();

    }

    @Override
    public void requestError(String error) {
        mRecommendRefresh.finishLoadmore();
        mRecommendRefresh.finishRefreshing();
    }

    @Override
    public void onClickItem(int poist) {

    }

    @Override
    public void onClickItemAdd(int poist, TextView addText) {

    }

    @Override
    public void onClickItemReduce(int poist, TextView jianText) {

    }
}
