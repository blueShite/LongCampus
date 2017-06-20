package com.example.longhengyu.longcampus.Person;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseFragment;
import com.example.longhengyu.longcampus.Person.Adapter.PersonAdapter;
import com.example.longhengyu.longcampus.Person.Bean.PersonBean;
import com.example.longhengyu.longcampus.Person.Interface.PersonInterface;
import com.example.longhengyu.longcampus.Person.Presenter.PersonPresenter;
import com.example.longhengyu.longcampus.PersonSubs.Collection.CollectionActivity;
import com.example.longhengyu.longcampus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by longhengyu on 2017/4/20.
 */

public class PersonFragment extends BaseFragment implements PersonInterface {

    @BindView(R.id.recycle_my)
    RecyclerView mRecycleMy;

    private List<PersonBean> mList = new ArrayList<>();
    private PersonPresenter mPresenter = new PersonPresenter();

    public static PersonFragment newInstance(String info) {
        Bundle args = new Bundle();
        PersonFragment fragment = new PersonFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_person;
    }

    @Override
    protected void initView() {

        ButterKnife.bind(this, convertView);
        customView();

    }

    @Override
    protected void initData() {

        mList = mPresenter.returnPersonItemData();
        PersonAdapter adapter = (PersonAdapter)mRecycleMy.getAdapter();
        adapter.reloadItem(mList);

    }

    private void customView(){

        mPresenter.setContext(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecycleMy.setLayoutManager(manager);
        PersonAdapter adapter = new PersonAdapter(mList,getContext(),this);
        mRecycleMy.setAdapter(adapter);

    }

    @Override
    public void onClickHeaderView(int headerIndex) {
        switch (headerIndex){
            case 0:
                Intent collectionIntent = new Intent(getActivity(), CollectionActivity.class);
                getActivity().startActivity(collectionIntent);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClickItem(int itemIndex) {

    }

    @Override
    public void onClickLogout() {

    }
}
