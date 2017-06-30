package com.example.longhengyu.longcampus.PersonSubs.Coupon.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.Bean.CouponBean;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.Interface.CouponInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/30.
 */

public class CouponPresenter extends BasePresenter {

    private CouponInterface mInterface;

    public CouponPresenter(CouponInterface anInterface){
        mInterface = anInterface;
    }

    public void requestList(String uId,String page,String flag){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("uid",uId);
        map.put("rows","10");
        map.put("page",page);
        map.put("flag",flag);
        RequestTools.getInstance().postRequest("/api/getUsercoupon.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
                mInterface.requestCouponError("请求失败");
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                super.onResponse(response, id);
                if(response.isRes()){
                    List<CouponBean> list = JSON.parseArray(response.getData(),CouponBean.class);
                    mInterface.requestCouponList(list);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                    mInterface.requestCouponError(response.getMes());
                }
            }
        });

    }
}
