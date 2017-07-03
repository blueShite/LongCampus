package com.example.longhengyu.longcampus.ShopCartList.Presenter;

import android.util.Log;

import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.ShopCart.Interface.ShopCartInterface;
import com.example.longhengyu.longcampus.ShopCartList.Interface.ShopCartListInterface;

import java.util.HashMap;
import java.util.Map;

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
                    Log.e("购物车数据",response.getData());
                }else{

                }
            }
        });
    }
}
