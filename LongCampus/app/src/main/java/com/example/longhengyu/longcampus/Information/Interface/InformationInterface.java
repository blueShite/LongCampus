package com.example.longhengyu.longcampus.Information.Interface;

import com.example.longhengyu.longcampus.Information.Bean.InformationBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/22.
 */

public interface InformationInterface {

    public void requestSuccess(List<InformationBean> bannerList,List<InformationBean> itemList);

    public void onClickHeader(int poist);

    public void onClickitem(int poist);

}
