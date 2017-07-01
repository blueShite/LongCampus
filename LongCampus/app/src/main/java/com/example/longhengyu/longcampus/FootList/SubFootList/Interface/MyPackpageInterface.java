package com.example.longhengyu.longcampus.FootList.SubFootList.Interface;

import android.widget.TextView;

import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageClassesBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/29.
 */

public interface MyPackpageInterface {

    void requestClassesSuccess(List<PackpageClassesBean> list,List<FeatureBean> commodityList);

    void requestCommoditySuccess(List<FeatureBean> list);

    void requestCommodityError(String error);

    void onClickClasses(int poist);

    void onClickMenu(int poist);

    void onClickCollection(int poist);

    void onClickAddShopCart(int poist, TextView numTextView);

    void onClickReduxShopCart(int poist, TextView numTextView);
}
