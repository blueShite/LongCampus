package com.example.longhengyu.longcampus.Information;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.Base.BaseFragment;
import com.example.longhengyu.longcampus.Information.Adapter.InformationAdapter;
import com.example.longhengyu.longcampus.Information.Bean.InformationBean;
import com.example.longhengyu.longcampus.Information.InformationDetail.InformationDetailActivity;
import com.example.longhengyu.longcampus.Information.Interface.InformationInterface;
import com.example.longhengyu.longcampus.Information.Presenter.InformationPresenter;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by longhengyu on 2017/4/20.
 */

public class InformationFragment extends SupportFragment implements InformationInterface {

    @BindView(R.id.recyclerview_information)
    RecyclerView mRecyclerviewInformation;
    @BindView(R.id.refreshLayout_information)
    TwinklingRefreshLayout mRefreshLayoutInformation;

    List<InformationBean> mbannerList = new ArrayList<>();
    List<InformationBean> mitemList = new ArrayList<>();

    InformationPresenter mInformationPresenter = new InformationPresenter(this);

    public static InformationFragment newInstance(String info) {
        Bundle args = new Bundle();
        InformationFragment fragment = new InformationFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_information, container, false);
        ButterKnife.bind(this, view);
        customView();
        mInformationPresenter.requestBanner();
        return view;
    }

    private void customView(){


        mInformationPresenter.setContext(getContext());

        //定制recycle
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerviewInformation.setLayoutManager(manager);
        InformationAdapter adapter = new InformationAdapter(mbannerList,mitemList,getContext(),this);
        mRecyclerviewInformation.setAdapter(adapter);


        //定制刷新加载
        SinaRefreshView headerView = new SinaRefreshView(getContext());
        headerView.setArrowResource(R.drawable.arrow);
        headerView.setTextColor(0xff745D5C);
        mRefreshLayoutInformation.setHeaderView(headerView);
        mRefreshLayoutInformation.setOnRefreshListener(new RefreshListenerAdapter(){
            @Override
            public void onRefresh(final TwinklingRefreshLayout refreshLayout) {
                mInformationPresenter.requestBanner();
            }

            @Override
            public void onLoadMore(final TwinklingRefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayoutInformation.finishLoadmore();
                    }
                },2000);
            }
        });
    }


    @Override
    public void requestSuccess(List<InformationBean> bannerList, List<InformationBean> itemList) {

        mRefreshLayoutInformation.finishRefreshing();
        mitemList = itemList;
        mbannerList = bannerList;
        mitemList.add(0,new InformationBean());
        InformationAdapter adapter = (InformationAdapter) mRecyclerviewInformation.getAdapter();
        adapter.reloadItem(itemList,mbannerList);

    }

    @Override
    public void onClickHeader(int poist) {

        Intent intent = new Intent(getActivity(), InformationDetailActivity.class);
        intent.putExtra("informationBean",mbannerList.get(poist));
        startActivity(intent);
    }

    @Override
    public void onClickitem(int poist) {

        Intent intent = new Intent(getActivity(), InformationDetailActivity.class);
        intent.putExtra("informationBean",mitemList.get(poist));
        startActivity(intent);
    }
}
