package com.example.longhengyu.longcampus.Person;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.Base.BaseFragment;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.Person.Adapter.PersonAdapter;
import com.example.longhengyu.longcampus.Person.Bean.PersonBalanceBean;
import com.example.longhengyu.longcampus.Person.Bean.PersonBean;
import com.example.longhengyu.longcampus.Person.Interface.PersonInterface;
import com.example.longhengyu.longcampus.Person.Presenter.PersonPresenter;
import com.example.longhengyu.longcampus.PersonSubs.Collection.CollectionActivity;
import com.example.longhengyu.longcampus.PersonSubs.Integral.IntegralActivity;
import com.example.longhengyu.longcampus.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by longhengyu on 2017/4/20.
 */

public class PersonFragment extends SupportFragment implements PersonInterface {

    @BindView(R.id.recycle_my)
    RecyclerView mRecycleMy;

    private List<PersonBean> mList = new ArrayList<>();
    private PersonPresenter mPresenter = new PersonPresenter(this);
    private PersonBalanceBean mBalanceBean;

    public static PersonFragment newInstance(String info) {
        Bundle args = new Bundle();
        PersonFragment fragment = new PersonFragment();
        args.putString("info", info);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_person, container, false);
        ButterKnife.bind(this, view);
        customView();
        mList = mPresenter.returnPersonItemData();
        mPresenter.requestBalance(LoginManage.getInstance().getLoginBean().getId());
        PersonAdapter adapter = (PersonAdapter)mRecycleMy.getAdapter();
        adapter.reloadItem(mList);
        return view;
    }

    private void customView(){

        mPresenter.setContext(getContext());
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecycleMy.setLayoutManager(manager);
        PersonAdapter adapter = new PersonAdapter(mList,getContext(),this);
        mRecycleMy.setAdapter(adapter);

    }

    @Override
    public void requestPersonData(PersonBalanceBean balanceBean) {
        PersonAdapter adapter = (PersonAdapter) mRecycleMy.getAdapter();
        mBalanceBean = balanceBean;
        adapter.reloadHeader(balanceBean);
    }

    @Override
    public void onClickHeaderView(int headerIndex) {
        switch (headerIndex){
            case 0:
                Intent collectionIntent = new Intent(getActivity(), CollectionActivity.class);
                getActivity().startActivity(collectionIntent);
                break;
            case 1:
                Intent integralIntent = new Intent(getActivity(), IntegralActivity.class);
                if(mBalanceBean==null){
                    integralIntent.putExtra("integral",LoginManage.getInstance().getLoginBean().getBalance());
                }else {
                    integralIntent.putExtra("integral",mBalanceBean.getIntegral());
                }
                getActivity().startActivity(integralIntent);
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
