package com.example.longhengyu.longcampus.PersonSubs.SetPerson.Presenter;

import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Bean.SetPersonBean;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Interface.SetPersonInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longhengyu on 2017/6/23.
 */

public class SetPersonPresenter extends BasePresenter {

    private SetPersonInterface mInterface;

    public SetPersonPresenter(SetPersonInterface anInterface){
        mInterface = anInterface;
    }

    public List<SetPersonBean> addPersonData(){

        List<SetPersonBean> beanList = new ArrayList<>();

        beanList.add(new SetPersonBean("姓名", LoginManage.getInstance().getLoginBean().getNickname()));
        String sex;
        if(LoginManage.getInstance().getLoginBean().getSex().equals("1")){
            sex = "男";
        }else{
            sex = "女";
        }
        beanList.add(new SetPersonBean("性别",sex));
        beanList.add(new SetPersonBean("手机号",LoginManage.getInstance().getLoginBean().getPhone()));
        beanList.add(new SetPersonBean("学号",LoginManage.getInstance().getLoginBean().getStuid()));
        beanList.add(new SetPersonBean("生日",LoginManage.getInstance().getLoginBean().getBirth()));
        beanList.add(new SetPersonBean("我的地址",LoginManage.getInstance().getLoginBean().getAddress()));
        beanList.add(new SetPersonBean("学校",LoginManage.getInstance().getLoginBean().getSchool()));
        beanList.add(new SetPersonBean("民族",LoginManage.getInstance().getLoginBean().getNation()));
        beanList.add(new SetPersonBean("QQ",LoginManage.getInstance().getLoginBean().getQq()));
        beanList.add(new SetPersonBean("微信",LoginManage.getInstance().getLoginBean().getWechat()));
        beanList.add(new SetPersonBean("邮箱",LoginManage.getInstance().getLoginBean().getEmail()));
        return beanList;

    }

    public void requestSunmitPerson(List<SetPersonBean> list){

        String[] keyList = new String[]{"tname","",""};
        for (int i=0;i<list.size();i++){
            if(i==5){
                continue;
            }
        }

    }

}
