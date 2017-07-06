package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Event;

import com.example.longhengyu.longcampus.PersonSubs.Coupon.Bean.CouponBean;

/**
 * Created by longhengyu on 2017/7/6.
 */

public class CouponShopCartOrderEvent {
    public final CouponBean mCouponBean;

    public CouponShopCartOrderEvent(CouponBean couponBean) {
        mCouponBean = couponBean;
    }
}
