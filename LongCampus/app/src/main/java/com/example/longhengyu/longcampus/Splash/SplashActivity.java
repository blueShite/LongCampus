package com.example.longhengyu.longcampus.Splash;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.example.longhengyu.longcampus.Login.Bean.LoginBean;
import com.example.longhengyu.longcampus.Login.LoginActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.Tab.TabActivity;
import com.squareup.picasso.Picasso;

public class SplashActivity extends Activity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mImageView = (ImageView)findViewById(R.id.image_splash);
        Picasso.with(SplashActivity.this).load(R.drawable.splash).fit().centerCrop().into(mImageView);
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                enterHomeActivity();
            }
        }, 2000);
    }

    private void enterHomeActivity(){

        LoginBean loginBean = LoginManage.getInstance().getLoginBean();
        if(loginBean==null||loginBean.getPhone().isEmpty()){
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        Intent intent = new Intent(SplashActivity.this,TabActivity.class);
        startActivity(intent);
        finish();
    }


}
