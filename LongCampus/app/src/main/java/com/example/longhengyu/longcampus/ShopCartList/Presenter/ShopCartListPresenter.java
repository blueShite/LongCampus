package com.example.longhengyu.longcampus.ShopCartList.Presenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.ShopCart.Interface.ShopCartInterface;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartListBean;
import com.example.longhengyu.longcampus.ShopCartList.Interface.ShopCartListInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/7/3.
 */

public class ShopCartListPresenter extends BasePresenter {

    private ShopCartListInterface mInterface;

    public ShopCartListPresenter (ShopCartListInterface anInterface){
        mInterface = anInterface;
    }

    public void requestShopCartList(String uId, final String resId){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("uid",uId);
        map.put("res_cid",resId);
        RequestTools.getInstance().postRequest("/api/shopping_list.api.php", false, map, "", new RequestCallBack(mContext) {
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
                    Log.e("打印的数据",response.getData());
                    List<ShopCartListBean> list = JSON.parseArray(response.getData(),ShopCartListBean.class);
                    mInterface.requestShopListSucess(handleList(list));
                }else{
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });
    }

    public List<ShopCartItemBean> handleList(List<ShopCartListBean> list){

        List<ShopCartItemBean> itemBeanList = new ArrayList<>();
        for (ShopCartListBean listBean:list){
            ShopCartItemBean bean = new ShopCartItemBean();
            bean.setItemType("0");
            bean.setRes_name(listBean.getName());
            itemBeanList.add(bean);
            for (ShopCartItemBean itemBean:listBean.getItmes()){
                itemBean.setItemType("1");
                itemBeanList.add(itemBean);
            }
        }
        return itemBeanList;
    }
}
