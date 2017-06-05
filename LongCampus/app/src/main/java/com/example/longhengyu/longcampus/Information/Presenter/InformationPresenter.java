package com.example.longhengyu.longcampus.Information.Presenter;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Information.Bean.InformationBean;
import com.example.longhengyu.longcampus.Information.Interface.InformationInterface;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/4/22.
 */

public class InformationPresenter extends BasePresenter {

    private InformationInterface mInterface;
    private List<InformationBean> bannerList = new ArrayList<>();

    public InformationPresenter(InformationInterface informationInterface){

        mInterface = informationInterface;
    }

    public void requestBanner(){

        showDialog();
        RequestTools.getInstance().getRequest("/api/getNewsTop.api.php", false, null, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                requestItem();
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                if(response.isRes()){
                    bannerList = JSON.parseArray(response.getData(), InformationBean.class);
                    requestItem();
                }else {
                    Toasty.error(mContext,"轮播图请求失败").show();
                    requestItem();
                }
                super.onResponse(response, id);
            }
        });

    }

    public void requestItem(){

        RequestTools.getInstance().getRequest("/api/getHTinfo.api.php", false, null, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                if(response.isRes()){
                    List<InformationBean> list = JSON.parseArray(response.getData(),InformationBean.class);
                    mInterface.requestSuccess(bannerList,list);
                }else {

                    List<InformationBean> list = new ArrayList<InformationBean>();
                    mInterface.requestSuccess(bannerList,list);
                    Toasty.error(mContext,"资讯请求失败").show();

                }
                super.onResponse(response, id);
            }
        });
    }



}
