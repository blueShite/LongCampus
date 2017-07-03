package com.example.longhengyu.longcampus.Manage;

import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.PackpageClassesBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/7/3.
 */

public class ClassesManage {

    private static ClassesManage instance = new ClassesManage();
    private ClassesManage (){}
    public static ClassesManage getInstance() {
        return instance;
    }

    public List<PackpageClassesBean> mList;

    public List<PackpageClassesBean> returnClasses(){

        return mList;
    }

    public void saveClasses(List<PackpageClassesBean> list){
        mList = list;
    }
}
