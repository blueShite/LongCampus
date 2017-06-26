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
public class SaleFragment extends SupportFragment {

    @BindView(R.id.sale_fragment_recycler)
    RecyclerView mSaleFragmentRecycler;
    @BindView(R.id.sale_refresh)
    TwinklingRefreshLayout mSaleRefresh;
    private View mView;
    private SaleAdapter mAdapter;

    public SaleFragment(CanteenBean canteenBean) {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_sale, container, false);
        ButterKnife.bind(this, mView);
        customView();
        return mView;
    }

    private void customView() {

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mSaleFragmentRecycler.setLayoutManager(layoutManager);
        mAdapter = new SaleAdapter();
        mSaleFragmentRecycler.setAdapter(mAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
