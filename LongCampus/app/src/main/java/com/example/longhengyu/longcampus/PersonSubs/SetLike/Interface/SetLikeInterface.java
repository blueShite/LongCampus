package com.example.longhengyu.longcampus.PersonSubs.SetLike.Interface;

import com.example.longhengyu.longcampus.PersonSubs.SetLike.Bean.SetLikeBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/22.
 */

public interface SetLikeInterface {

    void requestSubmitLike(String likeStr);
    void requestSubmitHate(String hateStr);
    void requestFootTypeList(List<SetLikeBean> list);
    void requestFlavorList(List<SetLikeBean> list);

}
