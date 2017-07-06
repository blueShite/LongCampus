package com.example.longhengyu.longcampus.Person;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.longhengyu.longcampus.Base.BaseFragment;
import com.example.longhengyu.longcampus.Login.LoginActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.Person.Adapter.PersonAdapter;
import com.example.longhengyu.longcampus.Person.Bean.PersonBalanceBean;
import com.example.longhengyu.longcampus.Person.Bean.PersonBean;
import com.example.longhengyu.longcampus.Person.Interface.PersonInterface;
import com.example.longhengyu.longcampus.Person.Presenter.PersonPresenter;
import com.example.longhengyu.longcampus.PersonSubs.AboutUs.AboutUsActivity;
import com.example.longhengyu.longcampus.PersonSubs.Collection.CollectionActivity;
import com.example.longhengyu.longcampus.PersonSubs.Coupon.CouponActivity;
import com.example.longhengyu.longcampus.PersonSubs.Feedback.FeedbackActivity;
import com.example.longhengyu.longcampus.PersonSubs.Integral.IntegralActivity;
import com.example.longhengyu.longcampus.PersonSubs.SetLike.Bean.SetLikeBean;
import com.example.longhengyu.longcampus.PersonSubs.SetLike.SetLikeActivity;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.SetPersonActivity;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.Tools.ActivityCollector;

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
        PersonAdapter adapter = (PersonAdapter)mRecycleMy.getAdapter();
        adapter.reloadItem(mList);
        return view;
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.requestBalance(LoginManage.getInstance().getLoginBean().getId());
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
        switch (itemIndex){
            case 1:
                Intent setIntent = new Intent(getActivity(), SetPersonActivity.class);
                startActivity(setIntent);
                break;
            case 2:
                Intent couponIntent = new Intent(getActivity(), CouponActivity.class);
                couponIntent.putExtra("isSelect","0");
                startActivity(couponIntent);
                break;
            case 3:
                Intent intent = new Intent(getActivity(), SetLikeActivity.class);
                startActivity(intent);
                break;
            case 4:
                Intent feedIntent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(feedIntent);
                break;
            case 5:
                Intent aboutIntent = new Intent(getActivity(), AboutUsActivity.class);
                startActivity(aboutIntent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClickLogout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage("确定退出登录吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface anInterface, int i) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                LoginManage.getInstance().saveLoginBean(null);
                ActivityCollector.finishAll();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface anInterface, int i) {

            }
        });
        builder.show();
    }
}
