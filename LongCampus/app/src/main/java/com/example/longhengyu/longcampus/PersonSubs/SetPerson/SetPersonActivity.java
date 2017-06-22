package com.example.longhengyu.longcampus.PersonSubs.SetPerson;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetPersonActivity extends BaseActivity {

    @BindView(R.id.set_person_recycler)
    RecyclerView mSetPersonRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_person);
        ButterKnife.bind(this);
        customView();
    }

    private void customView(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(SetPersonActivity.this);
        mSetPersonRecycler.setLayoutManager(layoutManager);

    }
}
