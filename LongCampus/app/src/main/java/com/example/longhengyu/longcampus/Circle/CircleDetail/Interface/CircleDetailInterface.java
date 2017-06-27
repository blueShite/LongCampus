package com.example.longhengyu.longcampus.Circle.CircleDetail.Interface;

import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailHeaderBean;
import com.example.longhengyu.longcampus.Circle.CircleDetail.Bean.CircleDetailItemBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/4/28.
 */

public interface CircleDetailInterface {

    void requestHeaderData(CircleDetailHeaderBean headerBean);
    void requestSucess(List<CircleDetailItemBean> list);
    void requestError(String error);
    void requestCommentSucess(String commentStr);
}
