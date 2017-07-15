package com.example.longhengyu.longcampus.Home.SearchSchool.Interface;

import com.example.longhengyu.longcampus.Home.SearchSchool.Bean.SearchSchoolBean;

import java.util.List;

/**
 * Created by longhengyu on 2017/7/13.
 */

public interface SearchSchoolInterface {

    void requestHotSucess(List<SearchSchoolBean> list);

    void requestSearchSucess(List<SearchSchoolBean> list);

}
