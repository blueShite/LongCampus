package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
        return mView;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        page = "1";
        mPresenter.requestList(page,mCanteenBean.getRes_id());
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

    @Override
    public void onClickItem(int poist) {
        Intent intent = new Intent(getActivity(), FootDetailActivity.class);
        intent.putExtra("featureBean",mList.get(poist));
        intent.putExtra("isMyMenu","1");
        intent.putExtra("resId",mCanteenBean.getRes_id());
        startActivity(intent);
    }

    @Override
    public void onClickAddShopCart(int poist, final TextView numTextView) {
        final FeatureBean bean = mList.get(poist);
        final String numsStr = (Integer.parseInt(bean.getNums())+1)+"";
        ShopcartRequest.requestShopCart(mCanteenBean.getRes_id(),numsStr, bean.getMenu_id(), getContext(), new ShopCartChangeInterface() {
            @Override
            public void changeShopCart() {
                bean.setNums(numsStr);
                numTextView.setText(numsStr);
                EventBus.getDefault().post(new FootListShopEvent("更新购物车"));
            }
        });
    }

    @Override
    public void onClickReduxShopCart(int poist, final TextView numTextView) {
        final FeatureBean bean = mList.get(poist);
        if(Integer.parseInt(bean.getNums())<1){
            Toasty.error(getContext(),"已经是0了,不能再少了").show();
            return;
        }
        final String numsStr = (Integer.parseInt(bean.getNums())-1)+"";
        ShopcartRequest.requestShopCart(mCanteenBean.getRes_id(),numsStr, bean.getMenu_id(), getContext(), new ShopCartChangeInterface() {
            @Override
            public void changeShopCart() {
                bean.setNums(numsStr);
                numTextView.setText(numsStr);
                EventBus.getDefault().post(new FootListShopEvent("更新购物车"));
            }
        });
    }
}
