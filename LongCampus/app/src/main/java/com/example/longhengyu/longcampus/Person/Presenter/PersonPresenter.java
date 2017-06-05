package com.example.longhengyu.longcampus.Person.Presenter;

import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.Person.Bean.PersonBean;
import com.example.longhengyu.longcampus.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longhengyu on 2017/4/24.
 */

public class PersonPresenter extends BasePresenter {

    public List<PersonBean> returnPersonItemData(){

        List<PersonBean> list = new ArrayList<>();
        list.add(new PersonBean("",0));
        list.add(new PersonBean("个人资料", R.drawable.gerenziliao));
        list.add(new PersonBean("优惠券", R.drawable.youhuiquan));
        list.add(new PersonBean("饮食偏好", R.drawable.gerenxihao));
        list.add(new PersonBean("意见反馈", R.drawable.yijianfankui));
        list.add(new PersonBean("关于我们", R.drawable.guanyuwomen));
        list.add(new PersonBean("清楚缓存", R.drawable.qingchuhuancun));
        list.add(new PersonBean("",0));

        return list;
    }




}
