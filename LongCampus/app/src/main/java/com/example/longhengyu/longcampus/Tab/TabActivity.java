package com.example.longhengyu.longcampus.Tab;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Circle.CircleFragment;
import com.example.longhengyu.longcampus.Home.HomeFragment;
import com.example.longhengyu.longcampus.Information.InformationFragment;
import com.example.longhengyu.longcampus.Person.PersonFragment;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.Tab.Adapter.ViewPagerAdapter;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class TabActivity extends BaseActivity {

    private ViewPager viewPager;
    private BottomBar bottomBar;

    private long m_exitTime = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        initView();
    }

    private void initView(){

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        bottomBar = (BottomBar)findViewById(R.id.bottomBar);
        setupViewPager(viewPager);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.home:
                        viewPager.setCurrentItem(0,false);
                        break;
                    case R.id.Information:
                        viewPager.setCurrentItem(1,false);
                        break;
                    case R.id.Circle:
                        viewPager.setCurrentItem(2,false);
                        break;
                    case R.id.Person:
                        viewPager.setCurrentItem(3,false);
                        break;
                    default:
                        break;
                }
            }
        });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                bottomBar.selectTabAtPosition(position, true);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void setupViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(HomeFragment.newInstance("首页"));
        adapter.addFragment(InformationFragment.newInstance("资讯"));
        adapter.addFragment(CircleFragment.newInstance("圈子"));
        adapter.addFragment(PersonFragment.newInstance("我的"));
        viewPager.setAdapter(adapter);

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
