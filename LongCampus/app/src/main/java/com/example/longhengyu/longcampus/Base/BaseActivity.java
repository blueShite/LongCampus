package com.example.longhengyu.longcampus.Base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.longhengyu.longcampus.Tools.ActivityCollector;


/**
 * Created by longhengyu on 2017/3/8.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }

    public void setContextView(int viewType){
        setContextView(viewType);
    }

    public void showDialog(){
        if(mDialog==null){
            mDialog = new ProgressDialog(this);
            mDialog.setMessage("正在加载...");
        }
        dismissDialog();
        mDialog.show();
    }

    public void dismissDialog(){
        if(mDialog==null){
            return;
        }
        mDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

}
