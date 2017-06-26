package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.FootList.SubFootList.Adapter.SaleAdapter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.R;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeatureFragment extends SupportFragment {


    @BindView(R.id.feature_fragment_recycler)
    RecyclerView mFeatureFragmentRecycler;
    @BindView(R.id.feature_refresh)
    TwinklingRefreshLayout mFeatureRefresh;
    private View mView;
    private SaleAdapter mAdapter;

    public FeatureFragment(CanteenBean canteenBean) {
        // Required empty public constructor
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

    private void customView() {

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mFeatureFragmentRecycler.setLayoutManager(layoutManager);
        mAdapter = new SaleAdapter();
        mFeatureFragmentRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
