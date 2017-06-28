package com.example.longhengyu.longcampus.PersonSubs.Address.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.Address.Bean.AddressBean;
import com.example.longhengyu.longcampus.PersonSubs.Address.Interface.AddressListInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/28.
 */

public class AddressListPresenter extends BasePresenter {

    private AddressListInterface mInterface;
    public AddressListPresenter(AddressListInterface anInterface){
        mInterface = anInterface;
    }

    public void requestList(String uId){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("id",uId);
        RequestTools.getInstance().postRequest("/api/list_address.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
                mInterface.requestError("请求失败");

            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                super.onResponse(response, id);
                if(response.isRes()){
                    List<AddressBean> list = JSON.parseArray(response.getData(),AddressBean.class);
                    mInterface.requestSucess(list);
                }else {

                }
            }
        });

    }

}
