package com.example.longhengyu.longcampus.FootDetail.Presenter;

import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.FootDetail.Bean.FootDetailItemBean;
import com.example.longhengyu.longcampus.ShopCart.Bean.ShopCartBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longhengyu on 2017/4/28.
 */

public class FootDetailPresenter {

    public List<FootDetailItemBean> hanbleFootDetailData(ShopCartBean bean){

        List<FootDetailItemBean> list = new ArrayList<>();
        if(bean.getFoodma().length()>0){
            list.add(new FootDetailItemBean("主要食材",bean.getFoodma()));
        }
        if(bean.getTact().length()>0){
            list.add(new FootDetailItemBean("做法",bean.getTact()));
        }
        if(bean.getValue().length()>0){
            list.add(new FootDetailItemBean("营养价值",bean.getValue()));
        }
        if(bean.getEfficacy().length()>0){
            list.add(new FootDetailItemBean("独特功效",bean.getEfficacy()));
        }
        if(bean.getLike_id().length()>0){
            list.add(new FootDetailItemBean("口味",bean.getLike_id()));
        }
        if(bean.getBan().length()>0){
            list.add(new FootDetailItemBean("禁忌",bean.getBan()));
        }
        return list;
    }
}
