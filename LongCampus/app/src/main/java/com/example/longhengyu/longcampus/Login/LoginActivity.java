package com.example.longhengyu.longcampus.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.longhengyu.longcampus.Base.BaseActivity;
import com.example.longhengyu.longcampus.Login.Interface.LoginInterface;
import com.example.longhengyu.longcampus.Login.Presenter.LoginPresenter;
import com.example.longhengyu.longcampus.Login.Register.RegisterActivity;
import com.example.longhengyu.longcampus.R;
import com.example.longhengyu.longcampus.Tab.TabActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;

public class LoginActivity extends BaseActivity implements LoginInterface {

    @BindView(R.id.edit_login_account)
    EditText mEditLoginAccount;
    @BindView(R.id.edit_login_password)
    EditText mEditLoginPassword;
    @BindView(R.id.button_login_login)
    Button mButtonLoginLogin;
    @BindView(R.id.text_login_register)
    TextView mTextLoginRegister;

    private LoginPresenter mLoginPresenter = new LoginPresenter(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter.setContext(this);
        mEditLoginAccount.setText("13676917233");
        mEditLoginPassword.setText("123456");
        mTextLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.text_login_register, R.id.button_login_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_login_register:
                break;
            case R.id.button_login_login:
                if (mEditLoginAccount.getText().length() < 1) {
                    Toasty.error(LoginActivity.this, "请输入账号!").show();
                    return;
                }
                if (mEditLoginPassword.getText().length() < 1) {
                    Toasty.error(LoginActivity.this, "请输入密码!").show();
                    return;
                }
                mLoginPresenter.requestLogin(mEditLoginAccount.getText().toString(), mEditLoginPassword.getText().toString());
                break;
            default:
                break;
        }
    }

    @Override
    public void successLogin() {

        Toasty.success(LoginActivity.this, "登录成功").show();
        Intent intent = new Intent(LoginActivity.this, TabActivity.class);
        startActivity(intent);
        finish();

    }
}
