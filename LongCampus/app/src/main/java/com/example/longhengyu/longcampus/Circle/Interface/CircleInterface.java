package com.example.longhengyu.longcampus.Circle.Interface;

import com.example.longhengyu.longcampus.Circle.Bean.CircleHeaderBean;
import com.example.longhengyu.longcampus.Circle.Bean.CircleItemBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/22.
 */

public interface CircleInterface {

    public void requestSucess(List<CircleHeaderBean> bannerList, List<CircleItemBean> itemList);

    public void onClickHeader(int poist);

    public void onClickItem(int poist);

}
