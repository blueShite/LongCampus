package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.longhengyu.longcampus.FootDetail.FootDetailActivity;
import com.example.longhengyu.longcampus.FootList.Event.FootListShopEvent;
import com.example.longhengyu.longcampus.FootList.ShopCartRequest.ShopCartChangeInterface;
import com.example.longhengyu.longcampus.FootList.ShopCartRequest.ShopcartRequest;
import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.RecommendAdapter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.RecommendInterface;
import com.example.longhengyu.longcampus.FootList.SubFootList.Presenter.RecommendPresenter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends SupportFragment implements RecommendInterface {

    @BindView(R.id.recommend_fragment_recycler)
    RecyclerView mRecommendFragmentRecycler;
    @BindView(R.id.recommend_refresh)
    TwinklingRefreshLayout mRecommendRefresh;
    @BindView(R.id.recommend_fragment_classer_recycle)
    RecyclerView mRecommendFragmentClasserRecycle;

    private View mView;
    private List<FeatureBean> mList = new ArrayList<>();
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
        return mView;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        page = "1";
        mPresenter.requestList(page, mCanteenBean.getRes_id(), "1");
    }

    private void customView() {

        mPresenter.setContext(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecommendFragmentRecycler.setLayoutManager(manager);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        mRecommendFragmentClasserRecycle.setLayoutManager(manager1);
        mAdapter = new RecommendAdapter(mList, getContext(), this);
        mRecommendFragmentRecycler.setAdapter(mAdapter);


        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRecommendRefresh.setHeaderView(headerView);

        LoadingView loadingView = new LoadingView(getContext());
        mRecommendRefresh.setBottomView(loadingView);
        mRecommendRefresh.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                page = "1";
                mPresenter.requestList(page, mCanteenBean.getRes_id(), "1");
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                int pageIndex = Integer.parseInt(page) + 1;
                page = pageIndex + "";
                mPresenter.requestList(page, mCanteenBean.getRes_id(), "1");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void requestSucess(List<FeatureBean> list) {

        mRecommendRefresh.finishLoadmore();
        mRecommendRefresh.finishRefreshing();
        if (page.equals("1")) {
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
        Intent intent = new Intent(getActivity(), FootDetailActivity.class);
        intent.putExtra("featureBean", mList.get(poist));
        intent.putExtra("isMyMenu", "0");
        intent.putExtra("resId", mCanteenBean.getRes_id());
        startActivity(intent);
    }

    @Override
    public void onClickItemAdd(int poist, final TextView addText) {
        final FeatureBean bean = mList.get(poist);
        final String numsStr = (Integer.parseInt(bean.getNums()) + 1) + "";
        ShopcartRequest.requestShopCart(mCanteenBean.getRes_id(), numsStr, bean.getMenu_id(), getContext(), new ShopCartChangeInterface() {
            @Override
            public void changeShopCart() {
                bean.setNums(numsStr);
                addText.setText(numsStr);
                EventBus.getDefault().post(new FootListShopEvent("更新购物车"));
            }
        });
    }

    @Override
    public void onClickItemReduce(int poist, final TextView jianText) {
        final FeatureBean bean = mList.get(poist);
        if (Integer.parseInt(bean.getNums()) < 1) {
            Toasty.error(getContext(), "已经是0了,不能再少了").show();
            return;
        }
        final String numsStr = (Integer.parseInt(bean.getNums()) - 1) + "";
        ShopcartRequest.requestShopCart(mCanteenBean.getRes_id(), numsStr, bean.getMenu_id(), getContext(), new ShopCartChangeInterface() {
            @Override
            public void changeShopCart() {
                bean.setNums(numsStr);
                jianText.setText(numsStr);
                EventBus.getDefault().post(new FootListShopEvent("更新购物车"));
            }
        });
    }
}
