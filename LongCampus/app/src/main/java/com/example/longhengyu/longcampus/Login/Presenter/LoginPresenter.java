package com.example.longhengyu.longcampus.Login.Presenter;

import com.alibaba.fastjson.JSON;
import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Login.Bean.LoginBean;
import com.example.longhengyu.longcampus.Login.Interface.LoginInterface;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.Tools.MyApplication;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/4/19.
 */

public class LoginPresenter extends BasePresenter {

    private LoginInterface mInterface;

    public LoginPresenter(LoginInterface anInterface){
        mInterface = anInterface;
    }

    public void requestLogin(String account,String password){

        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("userid",account);
        map.put("password",password);
        RequestTools.getInstance().postRequest("/api/loginPro.api.php", false, map, "", new RequestCallBack(mContext) {
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

                    mInterface.successLogin();
                    LoginBean bean = JSON.parseArray(response.getData(),LoginBean.class).get(0);
                    LoginManage.getInstance().saveLoginBean(bean);

                }else {
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });
    }
}
