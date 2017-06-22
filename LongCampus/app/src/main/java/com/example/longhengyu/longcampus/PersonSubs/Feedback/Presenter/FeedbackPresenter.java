package com.example.longhengyu.longcampus.PersonSubs.Feedback.Presenter;

import com.example.longhengyu.longcampus.Base.BasePresenter;
import com.example.longhengyu.longcampus.NetWorks.RequestBean;
import com.example.longhengyu.longcampus.NetWorks.RequestCallBack;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.PersonSubs.Feedback.Interface.FeedbackInterface;
import com.example.longhengyu.longcampus.PersonSubs.SetLike.Bean.SetLikeBean;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;
import okhttp3.Call;

/**
 * Created by longhengyu on 2017/6/22.
 */

public class FeedbackPresenter extends BasePresenter {

    private FeedbackInterface mInterface;

    public FeedbackPresenter(FeedbackInterface anInterface){
        mInterface = anInterface;
    }

    public void requestSubmit(String feedbackStr,String phone,String uId){
        showDialog();
        Map<String,String> map = new HashMap<>();
        map.put("body",feedbackStr);
        map.put("connect",phone);
        map.put("uid",uId);
        RequestTools.getInstance().postRequest("/api/feedback.api.php", false, map, "", new RequestCallBack(mContext) {
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
                    Toasty.success(mContext,"提交成功").show();
                    mInterface.requestSubmitSucess();
                }else{
                    Toasty.error(mContext,response.getMes()).show();
                }
            }
        });

    }

}
