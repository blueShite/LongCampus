package com.example.longhengyu.longcampus.FootList.SubFootList.Interface;

import android.widget.TextView;

import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/26.
 */

public interface RecommendInterface {

    void requestSucess(List<ShopCartBean> list);

    void requestError(String error);

    void onClickItem(int poist);

    void onClickItemAdd(int poist , TextView addText);

    void onClickItemReduce(int poist, TextView jianText);
}
