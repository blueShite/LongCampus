package com.example.longhengyu.longcampus.Home.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Home.Bean.CanteenBean;
import com.example.longhengyu.longcampus.Home.Interface.HomeInterface;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/4/20.
 */

public class HomePresenter extends BasePresenter {

    private HomeInterface mHomeInterface;

    public HomePresenter(HomeInterface homeInterface){
        mHomeInterface = homeInterface;
    }

    public void requestHomeData(String page,String schId){

        showDialog();
        Map<String,String> map = new HashMap<>();
        if(LoginManage.getInstance().getLoginBean().getSch_id()==null
                ||LoginManage.getInstance().getLoginBean().getSch_id().isEmpty()){
            map.put("sch_id", "1");
        }else {
            map.put("sch_id", LoginManage.getInstance().getLoginBean().getSch_id());
        }
        map.put("page",page);
        RequestTools.getInstance().postRequest("/api/getResList.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                super.onError(call, e, id);
                mHomeInterface.requestHomeDataError("请求失败");
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                if(response.isRes()){
                    Log.e("首页数据",response.getData());
                    List<CanteenBean> list = JSON.parseArray(response.getData(),CanteenBean.class);
                    mHomeInterface.requestHomeDataSucess(list);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                    mHomeInterface.requestHomeDataError(response.getMes());
                }
                super.onResponse(response, id);
            }
        });
    }
}
