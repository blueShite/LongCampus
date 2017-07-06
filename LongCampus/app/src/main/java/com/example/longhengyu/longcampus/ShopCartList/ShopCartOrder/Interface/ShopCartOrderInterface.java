package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Interface;

import com.example.longhengyu.longcampus.PersonSubs.Address.Bean.AddressBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/7/5.
 */

public interface ShopCartOrderInterface {

    void requestAddressList(List<AddressBean> list);

    void onClickCoupon();

    void onClickTime();

    void onClickGiveType(int type);

    void itemEditText(int poist,String editText);

}
