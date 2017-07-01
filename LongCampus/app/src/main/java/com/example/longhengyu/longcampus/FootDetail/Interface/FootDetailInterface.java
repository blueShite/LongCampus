package com.example.longhengyu.longcampus.FootDetail.Interface;

import android.widget.TextView;

import com.example.longhengyu.longcampus.FootDetail.Bean.FootDetailBean;

/**
 * Created by longhengyu on 2017/7/1.
 */

public interface FootDetailInterface {

    void requestDetailSucess(FootDetailBean detailBean);

    void onClickAdd(TextView numTextView);

    void onClickRedux(TextView numTextView);

}
