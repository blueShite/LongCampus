package com.example.longhengyu.longcampus.FootList.SubFootList;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.R;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPackageFragment extends SupportFragment {

    private View mView;

    public MyPackageFragment(CanteenBean canteenBean) {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_my_package, container, false);
        ButterKnife.bind(this,mView);
        return mView;
    }

}
