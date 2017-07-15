package com.example.longhengyu.longcampus.PersonSubs.Collection.Interface;

import com.example.longhengyu.longcampus.PersonSubs.Collection.Bean.CollectionBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/20.
 */

public interface CollectionInterface {

    void requestCollectionSucess(List<CollectionBean> list,String page);
    void requestCollectionError(String error);
    void onClickCollectSelect(int index);
    void requestCancelCollection(int index);
    void onClickItemView(int poist);

}
