package com.example.longhengyu.longcampus.FootList.SubFootList.Interface;

import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/27.
 */

public interface FeatureInterface {

    void requestSucess(List<FeatureBean> list);
    void requestError(String error);

}