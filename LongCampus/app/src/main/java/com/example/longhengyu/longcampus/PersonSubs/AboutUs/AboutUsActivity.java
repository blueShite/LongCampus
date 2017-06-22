package com.example.longhengyu.longcampus.PersonSubs.AboutUs;

import android.os.Bundle;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.R;

import butterknife.ButterKnife;

public class AboutUsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
    }
}
