package com.example.longhengyu.longcampus.FootList.SubFootList.Interface;

import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/27.
 */

public interface SaleInterface  {

    void requestSucess(List<ShopCartBean> list);

    void onClickAddShopCart(int poist);
}
