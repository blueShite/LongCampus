package com.example.longhengyu.longcampus.PersonSubs.SetLike.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.SetLike.Bean.SetLikeBean;
import com.example.longhengyu.longcampus.PersonSubs.SetLike.Interface.SetLikeInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/22.
 */

public class SetLikePresenter extends BasePresenter {

    private SetLikeInterface mInterface;

    public SetLikePresenter(SetLikeInterface anInterface){
        mInterface = anInterface;
    }

    public void requestFootType(){
        RequestTools.getInstance().postRequest("/api/getFoodmaList.api.php", false, null, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                super.onResponse(response, id);
                if(response.isRes()){
                    List<SetLikeBean> list = JSON.parseArray(response.getData(),SetLikeBean.class);
                    mInterface.requestFootTypeList(list);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });
    }

    public void requestFlavor(){
        RequestTools.getInstance().postRequest("/api/getPreferenceList.api.php", false, null, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                super.onResponse(response, id);
                if(response.isRes()){
                    List<SetLikeBean> list = JSON.parseArray(response.getData(),SetLikeBean.class);
                    mInterface.requestFlavorList(list);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });
    }

    public void requestSubmitLike(final String likeStr, final String hadeStr, final String uId){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("perf",likeStr);
        map.put("uid",uId);
        RequestTools.getInstance().postRequest("/api/addPersonPerf.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                super.onResponse(response, id);
                if(response.isRes()){
                    requestSubmitHate(likeStr,hadeStr,uId);
                }else {
                    dismissDialog();
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });
    }

    public void requestSubmitHate(final String likeStr, final String hateStr, String uId){
        Map<String,String> map = new HashMap<>();
        map.put("hate",hateStr);
        map.put("uid",uId);
        RequestTools.getInstance().postRequest("/api/addPersonHate.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                super.onResponse(response, id);
                if(response.isRes()){
                    mInterface.requestSubmit(likeStr,hateStr);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });
    }
}
