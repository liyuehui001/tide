package com.predict.tide.black.tide.tideCode.module.loginActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.predict.tide.black.tide.R;
import com.predict.tide.black.tide.staticVar.RequestStr;
import com.predict.tide.black.tide.tideCode.base.TideBaseActivity;
import com.predict.tide.black.tide.tideCode.module.loginActivity.bean.UserInfoDto;
import com.predict.tide.black.tide.tideCode.module.mainActivity.MainActivity;

import java.util.WeakHashMap;

public class LoginActivity extends TideBaseActivity<LoginPresenter> implements LoginContract,View.OnClickListener {
    private SharedPreferences sharedPreferences;

    public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static final String LOGIN_FAIL = "LOGIN_FAIL";

    private EditText et_account;
    private EditText et_password;
    private Button btn_login;
    private CheckBox checkBox_password;
    private CheckBox checkBox_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sharedPreferences = getSharedPreferences("tide", Context.MODE_PRIVATE);
        _init();

    }


    private void _init(){
        //目前，不允许修改密码
        if (!sharedPreferences.getString(RequestStr.UserInfoStore.USER_NAME,"").equals("") &&
                !sharedPreferences.getString(RequestStr.UserInfoStore.PASS_WORD,"").equals("") &&
                sharedPreferences.getBoolean(RequestStr.UserInfoStore.isRemeberPass,false)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }

        et_account = (EditText)findViewById(R.id.et_account);
        et_password = (EditText)findViewById(R.id.et_password);
        btn_login = (Button)findViewById(R.id.btn_login);
        checkBox_password = (CheckBox)findViewById(R.id.checkBox_password);
        checkBox_login = (CheckBox)findViewById(R.id.checkBox_login);

        btn_login.setOnClickListener(this);

        checkBox_password.setChecked(sharedPreferences.getBoolean(RequestStr.UserInfoStore.isRemeberPass,false));
        checkBox_login.setChecked(sharedPreferences.getBoolean(RequestStr.UserInfoStore.isRemeberPass,false));

    }

    @Override
    public LoginPresenter getmPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void login(WeakHashMap<String, String> map) {
        map.put("name",et_account.getText().toString().trim());
        map.put("password",et_password.getText().toString().trim());
        mPresenter.login(map);
    }

    @Override
    public void onClick(View v) {
        int viewid = v.getId();
        switch(viewid){
            case R.id.btn_login:
                WeakHashMap<String,String> map = new WeakHashMap<>();
                this.login(map);
                break;
                default:
                    break;
        }
    }


    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(LoginActivity.LOGIN_SUCCESS)
            }
    )
    public void getLoginSuccess(UserInfoDto userInfoDto){
        Toast.makeText(this,userInfoDto.getMessage(),Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(RequestStr.UserInfoStore.USER_NAME, userInfoDto.getUsername());
        editor.putString(RequestStr.UserInfoStore.PASS_WORD, userInfoDto.getPassword());
        editor.commit();
        this.finish();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
    }


    @Subscribe(
            thread = EventThread.MAIN_THREAD,
            tags = {
                    @Tag(LoginActivity.LOGIN_FAIL)
            }
    )
    public void getLoginFail(UserInfoDto userInfoDto){
        Toast.makeText(this,userInfoDto.getMessage(),Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (checkBox_login.isChecked()){
            sharedPreferences.edit().putBoolean(RequestStr.UserInfoStore.zidong_login,true).commit();
        }else{
            sharedPreferences.edit().putBoolean(RequestStr.UserInfoStore.zidong_login,false).commit();
        }

        if (checkBox_password.isChecked()){
            sharedPreferences.edit().putBoolean(RequestStr.UserInfoStore.isRemeberPass,true).commit();
        }else{
            sharedPreferences.edit().putBoolean(RequestStr.UserInfoStore.isRemeberPass,false).commit();
        }

    }
}
