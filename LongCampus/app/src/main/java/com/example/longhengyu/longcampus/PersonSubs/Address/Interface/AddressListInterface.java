package com.example.longhengyu.longcampus.PersonSubs.Address.Interface;

import com.example.longhengyu.longcampus.PersonSubs.Address.Bean.AddressBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/6/28.
 */

public interface AddressListInterface {

    void requestSucess(List<AddressBean> list);

    void requestError(String error);

    void requestDeleteSucess(int poist);

    void onClickSelectBtn(int poist);

    void onClickSeting(int poist);

    void onClickDelete(int poist);

}
