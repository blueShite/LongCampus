package com.example.longhengyu.longcampus.Circle.CircleDetail.Presenter;

import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailHeaderBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailItemBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Interface.CircleDetailInterface;
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
 * Created by longhengyu on 2017/4/28.
 */

public class CircleDetailPresenter extends BasePresenter {

    private CircleDetailInterface mInterface;
    private List<CircleDetailItemBean> mList = new ArrayList<>();

    public CircleDetailPresenter(CircleDetailInterface circleDetailInterface){
        mInterface = circleDetailInterface;
    }

    public void requestData(final String groupId, String page){

        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("group_id",groupId);
        map.put("com_page",page);
        RequestTools.getInstance().postRequest("/api/getGroupCommentList.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                requestHeaderData(groupId);
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                super.onResponse(response, id);
                if(response.isRes()){
                    mList = JSON.parseArray(response.getData(),CircleDetailItemBean.class);
                }else {
                    Toasty.error(mContext,"获取列表失败").show();
                }
                requestHeaderData(groupId);
            }
        });

    }

    public void requestHeaderData(String groupId){

        final Map<String,String> map = new HashMap<>();
        map.put("gid",groupId);
        RequestTools.getInstance().postRequest("/api/getDieCircleCon.api.php", false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                dismissDialog();
                mInterface.requestSucess(new CircleDetailHeaderBean(),mList);
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                dismissDialog();
                super.onResponse(response, id);
                if(response.isRes()){

                    CircleDetailHeaderBean bean = JSON.parseArray(response.getData(),CircleDetailHeaderBean.class).get(0);
                    mInterface.requestSucess(bean,mList);
                }else {
                    Toasty.error(mContext,"获取信息失败").show();
                    mInterface.requestSucess(new CircleDetailHeaderBean(),mList);
                }
            }
        });
    }

}
