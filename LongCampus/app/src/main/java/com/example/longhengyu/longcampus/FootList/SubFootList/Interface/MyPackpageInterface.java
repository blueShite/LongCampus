package com.example.longhengyu.longcampus.FootList.SubFootList.Interface;

import android.widget.TextView;

import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageClassesBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageCommodityBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/29.
 */

public interface MyPackpageInterface {

    void requestClassesSuccess(List<PackpageClassesBean> list,List<PackpageCommodityBean> commodityList);

    void requestCommoditySuccess(List<PackpageCommodityBean> list);

    void requestCommodityError(String error);

    void onClickClasses(int poist);

    void onClickCollection(int poist);

    void onClickAddShopCart(int poist, TextView numTextView);

    void onClickReduxShopCart(int poist, TextView numTextView);
}