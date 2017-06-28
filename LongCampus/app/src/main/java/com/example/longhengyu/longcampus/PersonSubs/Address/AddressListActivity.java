package com.example.longhengyu.longcampus.PersonSubs.Address;

import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.Address.Adapter.AddressListAdapter;
import com.example.longhengyu.longcampus.PersonSubs.Address.Bean.AddressBean;
import com.example.longhengyu.longcampus.PersonSubs.Address.Interface.AddressListInterface;
import com.example.longhengyu.longcampus.PersonSubs.Address.Presenter.AddressListPresenter;
import com.example.longhengyu.longcampus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddressListActivity extends BaseActivity  implements AddressListInterface{

    @BindView(R.id.address_list_recycle)
    RecyclerView mAddressListRecycle;

    private AddressListPresenter mPresenter = new AddressListPresenter(this);
    private List<AddressBean> mList = new ArrayList<>();
    private AddressListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_list);
        ButterKnife.bind(this);
        customView();
    }

    private void customView(){

        mPresenter.setContext(AddressListActivity.this);

        LinearLayoutManager manager = new LinearLayoutManager(AddressListActivity.this);
        mAddressListRecycle.setLayoutManager(manager);
        mAdapter = new AddressListAdapter(mList,AddressListActivity.this,this);
        mAddressListRecycle.setAdapter(mAdapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.requestList(LoginManage.getInstance().getLoginBean().getId());
    }

    @OnClick(R.id.button_add_address_submit)
    public void onViewClicked() {
        Intent intent = new Intent(AddressListActivity.this,AddAddressActivity.class);
        startActivity(intent);
    }

    @Override
    public void requestSucess(List<AddressBean> list) {
        mList.clear();
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestError(String error) {

    }

    @Override
    public void onClickSelectBtn(int poist) {

    }

    @Override
    public void onClickSeting(int poist) {

    }

    @Override
    public void onClickDelete(int poist) {

    }
}
