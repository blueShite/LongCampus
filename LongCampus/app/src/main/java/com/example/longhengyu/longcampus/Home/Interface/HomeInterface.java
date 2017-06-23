package com.example.longhengyu.longcampus.Home.Interface;

import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/20.
 */

public interface HomeInterface  {

    void requestHomeDataSucess(List<CanteenBean> list);

    void requestHomeDataError(String error);

}
