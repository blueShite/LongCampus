package com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Presenter;

import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;
import com.example.longhengyu.longcampus.ShopCartList.ShopCartOrder.Interface.ShopCartOrderInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longhengyu on 2017/7/5.
 */

public class ShopCartOrderPresenter extends BasePresenter {

    private ShopCartOrderInterface mInterface;

    public ShopCartOrderPresenter(ShopCartOrderInterface anInterface){
        mInterface = anInterface;
    }

    public List<ShopCartItemBean> handleList(List<ShopCartItemBean> list){

        List<ShopCartItemBean> itemList = new ArrayList<>();

        for (ShopCartItemBean itemBean:list){
            if(itemBean.getItemType().equals("1")){
                int itemNum = Integer.parseInt(itemBean.getNum());
                for (int i=0;i<itemNum;i++){
                    ShopCartItemBean cartItemBean = new ShopCartItemBean();
                    cartItemBean.setNum("1");
                    cartItemBean.setItemType(itemBean.getItemType());
                    cartItemBean.setDish(itemBean.getDish());
                    cartItemBean.setFlag(itemBean.getFlag());
                    cartItemBean.setMenu_id(itemBean.getMenu_id());
                    cartItemBean.setRes_id(itemBean.getRes_id());
                    cartItemBean.setRes_name(itemBean.getRes_name());
                    cartItemBean.setRes_names(itemBean.getRes_names());
                    cartItemBean.setTotal(itemBean.getTotal());
                    itemList.add(cartItemBean);
                }
            }else {
                itemList.add(itemBean);
            }
        }
        return itemList;
    }

}
