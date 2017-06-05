package com.example.longhengyu.longcampus.Information.InformationDetail;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Information.Bean.InformationBean;
import com.example.longhengyu.longcampus.NetWorks.RequestTools;
import com.example.longhengyu.longcampus.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class InformationDetailActivity extends BaseActivity {

    @BindView(R.id.text_information_detail_titleName)
    TextView mTextInformationDetailTitleName;
    @BindView(R.id.text_information_detail_name)
    TextView mTextInformationDetailName;
    @BindView(R.id.text_information_detail_time)
    TextView mTextInformationDetailTime;
    @BindView(R.id.text_information_detail_from)
    TextView mTextInformationDetailFrom;
    @BindView(R.id.image_information_detail)
    ImageView mImageInformationDetail;
    @BindView(R.id.text_information_detail_sub)
    TextView mTextInformationDetailSub;

    InformationBean mInformationBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_detail);
        ButterKnife.bind(this);
        mInformationBean = (InformationBean) getIntent().getSerializableExtra("informationBean");
        initView();
    }

    private void initView(){

        mTextInformationDetailTitleName.setText(mInformationBean.getHtinfo());
        Picasso.with(InformationDetailActivity.this).load(RequestTools.BaseUrl+mInformationBean.getLitpic()).into(mImageInformationDetail);
        mTextInformationDetailName.setText(mInformationBean.getTitle());
        mTextInformationDetailTime.setText(mInformationBean.getDate());
        mTextInformationDetailFrom.setText("内容来自:"+mInformationBean.getForm()+"  "+"作者:"+mInformationBean.getAuthor());
        mTextInformationDetailSub.setText(mInformationBean.getBody());
    }


}
