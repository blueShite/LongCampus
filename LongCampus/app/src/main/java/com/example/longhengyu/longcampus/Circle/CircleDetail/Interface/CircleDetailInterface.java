package com.example.longhengyu.longcampus.Circle.CircleDetail.Interface;

import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailHeaderBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailItemBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/28.
 */

public interface CircleDetailInterface {

    public void requestSucess(CircleDetailHeaderBean headerBean, List<CircleDetailItemBean> list);

}
