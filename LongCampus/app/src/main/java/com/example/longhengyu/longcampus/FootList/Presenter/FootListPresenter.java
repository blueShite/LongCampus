package com.example.longhengyu.longcampus.FootList.Presenter;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.FootList.Interface.FootListInterface;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartHeaderBean;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/26.
 */

public class FootListPresenter extends BasePresenter {

    private FootListInterface mInterface;

    public FootListPresenter(FootListInterface anInterface){
        mInterface = anInterface;
    }

    public void requestFootListHeader(String resId){

        Map<String,String> map = new HashMap<>();
        map.put("resid",resId);
        RequestTools.getInstance().postRequest("/api/getResTop.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                super.onResponse(response, id);
                if(response.isRes()){
                    ShopCartHeaderBean headerBean = JSON.parseArray(response.getData(),ShopCartHeaderBean.class).get(0);
                    mInterface.requestSucess(headerBean);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }

            }
        });
    }

    public void requestShopCartNum(){

    }

}
