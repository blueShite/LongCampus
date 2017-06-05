package com.example.longhengyu.longcampus.ShopCart.Interface;

import android.widget.TextView;

import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartHeaderBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/25.
 */

public interface ShopCartInterface {

    public void requestItemSuccess(List<ShopCartBean> list, ShopCartHeaderBean headerBean,String tag);

    public void onClickHeader(int poist);

    public void onClickItem(int poist);

    public void onClickItemAdd(int poist , TextView addText);

    public void onClickItemReduce(int poist, TextView jianText);

}
