package com.example.longhengyu.longcampus.PersonSubs.Address.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.Address.Bean.AddressBean;
import com.example.longhengyu.longcampus.PersonSubs.Address.Interface.AddressListInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
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
                    for (AddressBean bean:list){
                        if(LoginManage.getInstance().getLoginBean().getLaddressId()==null||
                                LoginManage.getInstance().getLoginBean().getLaddressId().isEmpty()){
                            break;
                        }
                        if(LoginManage.getInstance().getLoginBean().getLaddressId().equals(bean.getId())){
                            bean.setSelect(true);
                            break;
                        }
                    }
                    mInterface.requestSucess(list);
                }else {

                }

            }
        });

    }
    public void requestDelect(String addressId, final int poist){

        Map<String,String> map = new HashMap<>();
        map.put("id",addressId);
        RequestTools.getInstance().postRequest("/api/del_address.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                super.onResponse(response, id);
                if(response.isRes()){
                    Toasty.success(mContext,"删除成功!").show();
                    mInterface.requestDeleteSucess(poist);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });

    }

}
