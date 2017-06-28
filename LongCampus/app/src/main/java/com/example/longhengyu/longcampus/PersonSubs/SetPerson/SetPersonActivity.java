package com.example.longhengyu.longcampus.PersonSubs.SetPerson;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Manage.LoginManage;
import com.example.longhengyu.longcampus.PersonSubs.Address.AddressListActivity;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Adapter.SetPersonAdapter;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Bean.SetPersonBean;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Interface.SetPersonInterface;
import com.example.longhengyu.longcampus.PersonSubs.SetPerson.Presenter.SetPersonPresenter;
import com.example.longhengyu.longcampus.R;

import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class SetPersonActivity extends BaseActivity implements SetPersonInterface {

    @BindView(R.id.set_person_recycler)
    RecyclerView mSetPersonRecycler;

    private SetPersonPresenter mPresenter = new SetPersonPresenter(this);
    private List<SetPersonBean> mList;
    private SetPersonAdapter mPersonAdapter;

    private String[] sexArray = new String[]{"男", "女"};
    int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_person);
        ButterKnife.bind(this);
        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);
        customView();
    }

    private void customView() {

        mPresenter.setContext(SetPersonActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SetPersonActivity.this);
        mSetPersonRecycler.setLayoutManager(layoutManager);
        mList = mPresenter.addPersonData();
        mPersonAdapter = new SetPersonAdapter(mList, SetPersonActivity.this, this);
        mSetPersonRecycler.setAdapter(mPersonAdapter);

    }

    private void showEditAlert(final int index, String hintStr) {
        LayoutInflater factory = LayoutInflater.from(SetPersonActivity.this);//提示框
        final View view = factory.inflate(R.layout.view_alert_edittext, null);//这里必须是final的
        final EditText edit = (EditText) view.findViewById(R.id.alert_editText);//获得输入框对象
        edit.setHint(hintStr);
        new AlertDialog.Builder(SetPersonActivity.this)
                .setTitle("提示")//提示框标题
                .setView(view)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface anInterface, int i) {
                        if (edit.getText().toString().length() < 1) {
                            Toasty.error(SetPersonActivity.this, "请输入您要填写的文字").show();
                            return;
                        }
                        SetPersonBean bean = mList.get(index);
                        bean.setSub(edit.getText().toString());
                        mPersonAdapter.notifyItemChanged(index + 1);
                    }
                })
                .setNegativeButton("取消", null).create().show();
    }

    @OnClick(R.id.text_setPerson_submit)
    public void onViewClicked() {
    }

    @Override
    public void onClickPersonItem(final int itemIndex) {
        switch (itemIndex) {
            case 0:
                showEditAlert(itemIndex, "请输入名称");
                break;
            case 1:
                int selectIndex;
                if(mList.get(1).getSub().equals("男")){
                    selectIndex = 0;
                }else {
                    selectIndex = 1;
                }
                new AlertDialog.Builder(this)
                        .setTitle("选择性别")
                        .setSingleChoiceItems(sexArray, selectIndex, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0){
                                    mList.get(1).setSub("男");
                                }else {
                                    mList.get(1).setSub("女");
                                }
                                mPersonAdapter.notifyItemChanged(itemIndex+1);
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case 2:
                showEditAlert(itemIndex, "请输入电话");
                break;
            case 3:
                showEditAlert(itemIndex, "请输入学号");
                break;
            case 4:
                new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker picker, int i, int i1, int i2) {
                        mYear = i;
                        mMonth = i1;
                        mDay = i2;
                        StringBuffer string = new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).append(" ");
                        mList.get(itemIndex).setSub(String.valueOf(string));
                        mPersonAdapter.notifyItemChanged(itemIndex+1);
                    }
                }, mYear, mMonth, mDay).show();
                break;
            case 5:
                Intent intent = new Intent(SetPersonActivity.this, AddressListActivity.class);
                startActivity(intent);
                break;
            case 6:
                showEditAlert(itemIndex, "请输入学校");
                break;
            case 7:
                String[] nationArray = new String[]{"汉族", "回族"};
                int index;
                if(mList.get(7).getSub().equals("汉族")){
                    index = 0;
                }else {
                    index = 1;
                }
                new AlertDialog.Builder(this)
                        .setTitle("选择民族")
                        .setSingleChoiceItems(nationArray, index, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(which==0){
                                    mList.get(7).setSub("汉族");
                                }else {
                                    mList.get(7).setSub("回族");
                                }
                                mPersonAdapter.notifyItemChanged(itemIndex+1);
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case 8:
                showEditAlert(itemIndex, "请输入QQ");
                break;
            case 9:
                showEditAlert(itemIndex, "请输入微信");
                break;
            case 10:
                showEditAlert(itemIndex, "请输入邮箱");
                break;
            default:
                break;
        }
    }

    @Override
    public void requestSubmitSucess() {

    }


}
