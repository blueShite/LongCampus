package com.example.longhengyu.longcampus.Tab;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.longhengyu.longcampus.Circle.CircleFragment;
import com.example.longhengyu.longcampus.Home.HomeFragment;
import com.example.longhengyu.longcampus.Information.InformationFragment;
import com.example.longhengyu.longcampus.Person.PersonFragment;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.Tools.ActivityCollector;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportActivity;

public class TabActivity extends SupportActivity {


    @BindView(R.id.contentView)
    FrameLayout mContentView;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;


    private long m_exitTime = 1;
    private HomeFragment mHomeFragment;
    private CircleFragment mCircleFragment;
    private InformationFragment mInformationFragment;
    private PersonFragment mPersonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ActivityCollector.addActivity(this);
        ButterKnife.bind(this);
        injectPages();
        initBottomBar();
    }

    private void injectPages() {


        mHomeFragment = HomeFragment.newInstance("首页");
        mInformationFragment = InformationFragment.newInstance("资讯");
        mCircleFragment = CircleFragment.newInstance("圈子");
        mPersonFragment = PersonFragment.newInstance("我的");
        loadMultipleRootFragment(R.id.contentView,0,mHomeFragment,mInformationFragment,mCircleFragment,mPersonFragment);

    }

    public BottomBar getBottomBar() {
        return mBottomBar;
    }

    private void initBottomBar() {

        mBottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId){
                    case R.id.home:
                        mHomeFragment = findFragment(HomeFragment.class);
                        if(mHomeFragment!=null){
                            showHideFragment(mHomeFragment);
                        }
                        break;
                    case R.id.Information:
                        mInformationFragment = findFragment(InformationFragment.class);
                        if(mInformationFragment!=null){
                            showHideFragment(mInformationFragment);
                        }
                        break;
                    case R.id.Circle:
                        mCircleFragment = findFragment(CircleFragment.class);
                        if(mCircleFragment!=null){
                            showHideFragment(mCircleFragment);
                        }
                        break;
                    case R.id.Person:
                        mPersonFragment = findFragment(PersonFragment.class);
                        if(mPersonFragment!=null){
                            showHideFragment(mPersonFragment);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * 再按一次退出程序
     */
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int keyCode = event.getKeyCode();
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - m_exitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                m_exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
