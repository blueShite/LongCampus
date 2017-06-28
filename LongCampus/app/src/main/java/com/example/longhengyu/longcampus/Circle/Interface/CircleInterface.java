package com.example.longhengyu.longcampus.Circle.Interface;

import com.example.longhengyu.longcampus.Circle.Bean.CircleHeaderBean;
import com.example.longhengyu.longcampus.Circle.Bean.CircleItemBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/22.
 */

public interface CircleInterface {

    void requestHeader(List<CircleHeaderBean> bannerList);

    void requestSucess(List<CircleItemBean> itemList);

    void requestError(String error);

    void onClickHeader(int poist);

    void onClickItem(int poist);

}
