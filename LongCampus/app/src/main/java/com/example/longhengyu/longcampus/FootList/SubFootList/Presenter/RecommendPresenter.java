package com.example.longhengyu.longcampus.FootList.SubFootList.Presenter;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.FootList.SubFootList.Interface.RecommendInterface;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/26.
 */

public class RecommendPresenter extends BasePresenter {

    private RecommendInterface mInterface;

    public RecommendPresenter(RecommendInterface anInterface){
        mInterface = anInterface;
    }

    public void requestList(String page, final String resId, String flag){

        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("page",page);
        map.put("res_id",resId);
        map.put("u_id", LoginManage.getInstance().getLoginBean().getId());
        map.put("flag",flag);
        RequestTools.getInstance().postRequest("/api/getMeal.api.php", false, map, "", new RequestCallBack(mContext) {
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
                    List<FeatureBean> list = JSON.parseArray(response.getData(),FeatureBean.class);
                    for (FeatureBean bean:list){
                        bean.setAddNum("0");
                    }
                    mInterface.requestSucess(list);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                    mInterface.requestError(response.getMes());
                }
            }
        });
    }

}
