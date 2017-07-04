package com.example.longhengyu.longcampus.ShopCartList.Interface;

import android.widget.TextView;

import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/7/3.
 */

public interface ShopCartListInterface {

    void requestShopListSucess(List<ShopCartItemBean> list);

    void onClickItemAdd(int poist , TextView addText);

    void onClickItemReduce(int poist, TextView jianText);

}
