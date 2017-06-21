package com.example.longhengyu.longcampus.PersonSubs.Integral.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Information.Bean.InformationBean;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.Integral.Bean.IntegralBean;
import com.example.longhengyu.longcampus.PersonSubs.Integral.Interface.IntegralInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/21.
 */

public class IntegralPresenter extends BasePresenter {

    private IntegralInterface mInterface;

    public IntegralPresenter(IntegralInterface anInterface){
        mInterface = anInterface;
    }

    public void requestIntegralData(String uId, final String page){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("uid",uId);
        map.put("page", page);
        RequestTools.getInstance().postRequest("/api/getInteList.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
                mInterface.requestImtegralError("请求失败");
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                super.onResponse(response, id);
                if(response.isRes()){
                    List<IntegralBean> list = JSON.parseArray(response.getData(),IntegralBean.class);
                    mInterface.requestIntegralSucess(list);
                }else {
                    mInterface.requestImtegralError(response.getMes());
                    Toasty.error(mContext,response.getMes()).show();
                }

            }
        });
    }

}
