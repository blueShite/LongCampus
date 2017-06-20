package com.example.longhengyu.longcampus.PersonSubs.Collection.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Bean.CollectionBean;
import com.example.longhengyu.longcampus.PersonSubs.Collection.Interface.CollectionInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/20.
 */

public class CollectionPresenter extends BasePresenter {

    private CollectionInterface mInterface;

    public CollectionPresenter(CollectionInterface anInterface){
        mInterface = anInterface;
    }

    public void requestCollection(final String page, String uId){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("page",page);
        map.put("u_id",uId);
        RequestTools.getInstance().postRequest("/api/getKeepMenu.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
                mInterface.requestCollectionError("请求失败");
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                super.onResponse(response, id);
                if(response.isRes()){
                    List<CollectionBean> list = JSON.parseArray(response.getData(),CollectionBean.class);
                    mInterface.requestCollectionSucess(list,page);
                }else {
                    mInterface.requestCollectionError(response.getMes());
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });

    }

}
