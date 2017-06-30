package com.example.longhengyu.longcampus.PersonSubs.Coupon.Interface;

import com.example.longhengyu.longcampus.PersonSubs.Coupon.Bean.CouponBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/30.
 */

public interface CouponInterface {


    void requestCouponList(List<CouponBean> list);

    void requestCouponError(String error);

}
