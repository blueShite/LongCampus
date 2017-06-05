package com.example.longhengyu.longcampus.ShopCart.Presenter;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartHeaderBean;
import com.example.longhengyu.longcampus.ShopCart.Interface.ShopCartInterface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/4/25.
 */

public class ShopCartPresenter extends BasePresenter {

    private ShopCartInterface mInterface;

    private List<ShopCartBean> mList;

    public ShopCartPresenter(ShopCartInterface shopCartInterface){

        mInterface = shopCartInterface;
    }

    public void requestHeaderData(final String resId,final String tag){

        Map<String,String> map = new HashMap<>();
        map.put("resid",resId);
        RequestTools.getInstance().postRequest("/api/getResTop.api.php", false, map, "", new RequestCallBack(mContext) {
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
                    ShopCartHeaderBean headerBean = JSON.parseArray(response.getData(),ShopCartHeaderBean.class).get(0);
                    headerBean.setImageId(R.drawable.tuijian);
                    headerBean.setHeaderText("今日推荐");
                    mInterface.requestItemSuccess(mList,headerBean,tag);
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }

            }
        });
    }

    public void requestItem(String path, String page, final String resId, final String flag){

        showDialog();
        Map<String,String> map = new HashMap<>();
        if(flag.equals("1")){
            map.put("page",page);
        }
        if(flag.equals("2")){
            map.put("row","100");
        }
        if(flag.equals("3")){
            map.put("page",page);
        }
        map.put("res_id",resId);
        map.put("u_id", LoginManage.getInstance().getLoginBean().getId());
        if(flag.equals("1")||flag.equals("2")){
            map.put("flag",flag);
        }
        RequestTools.getInstance().postRequest(path, false, map, "", new RequestCallBack(mContext) {
            @Override
            public void onError(Call call, Exception e, int id) {
                requestHeaderData(resId,flag);
                super.onError(call, e, id);
            }

            @Override
            public void onResponse(RequestBean response, int id) {
                super.onResponse(response, id);
                if(response.isRes()){
                    mList = JSON.parseArray(response.getData(),ShopCartBean.class);
                    for (int i=0;i<mList.size();i++){
                        ShopCartBean bean = mList.get(i);
                        bean.setAddNum("0");
                    }
                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }
                requestHeaderData(resId,flag);
            }
        });

    }


}
