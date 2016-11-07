package com.example.fate.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * Created by fate on 2016/10/27.
 * 欢迎界面：判断本地的preference是否有用户数据，有，自动登录，没有，跳转登录界面。
 */
public class WelcomeActivity extends Activity {
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                preferences=getSharedPreferences("user",MODE_PRIVATE);
                //有，直接登录
                if(preferences.contains("user_id") && preferences.contains("user_name") && preferences.contains("user_password")){
                    Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(intent);
                }
                //没有用户数据，跳转登录界面
                else{
                    Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0,5*1000);
    }
}
