package com.example.longhengyu.longcampus.ShopCartList;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.FootList.Event.FootListShopEvent;
import com.example.longhengyu.longcampus.FootList.ShopCartRequest.ShopCartChangeInterface;
import com.example.longhengyu.longcampus.FootList.ShopCartRequest.ShopcartRequest;
import com.example.longhengyu.longcampus.FootList.SubFootList.Bean.FeatureBean;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.ShopCartList.Adapter.ShopCartListAdapter;
import com.example.longhengyu.longcampus.ShopCartList.Bean.ShopCartItemBean;
import com.example.longhengyu.longcampus.ShopCartList.Interface.ShopCartListInterface;
import com.example.longhengyu.longcampus.ShopCartList.Presenter.ShopCartListPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class ShopCartListActivity extends BaseActivity implements ShopCartListInterface {

    @BindView(R.id.shopcart_list_recycle)
    RecyclerView mShopcartListRecycle;

    private ShopCartListPresenter mPresenter = new ShopCartListPresenter(this);
    private String mResId;
    private List<ShopCartItemBean> mList = new ArrayList<>();
    private ShopCartListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart_list);
        ButterKnife.bind(this);
        customView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestShopCartList(LoginManage.getInstance().getLoginBean().getId(),mResId);
    }

    private void customView(){

        mPresenter.setContext(ShopCartListActivity.this);
        mResId = getIntent().getStringExtra("resId");
        LinearLayoutManager layoutManager = new LinearLayoutManager(ShopCartListActivity.this);
        mShopcartListRecycle.setLayoutManager(layoutManager);
        mAdapter = new ShopCartListAdapter(mList,ShopCartListActivity.this,this);
        mShopcartListRecycle.setAdapter(mAdapter);
    }

    @OnClick(R.id.button_shopcart_list_submit)
    public void onViewClicked() {
    }

    @Override
    public void requestShopListSucess(List<ShopCartItemBean> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClickItemAdd(int poist, final TextView addText) {
        final ShopCartItemBean bean = mList.get(poist);
        final String numsStr = (Integer.parseInt(bean.getNum()) + 1) + "";
        ShopcartRequest.requestShopCart(bean.getRes_id(), numsStr, bean.getMenu_id(), ShopCartListActivity.this, new ShopCartChangeInterface() {
            @Override
            public void changeShopCart() {
                bean.setNum(numsStr);
                addText.setText(numsStr);
            }
        });
    }

    @Override
    public void onClickItemReduce(final int poist, final TextView jianText) {
        final ShopCartItemBean bean = mList.get(poist);
        if (Integer.parseInt(bean.getNum()) < 1) {
            Toasty.error(ShopCartListActivity.this, "已经是0了,不能再少了").show();
            return;
        }
        final String numsStr = (Integer.parseInt(bean.getNum()) - 1) + "";
        ShopcartRequest.requestShopCart(bean.getRes_id(), numsStr, bean.getMenu_id(), ShopCartListActivity.this, new ShopCartChangeInterface() {
            @Override
            public void changeShopCart() {
                if(numsStr.equals("0")){
                    mList.remove(poist);
                    mAdapter.notifyDataSetChanged();
                    return;
                }
                bean.setNum(numsStr);
                jianText.setText(numsStr);
            }
        });
    }
}
