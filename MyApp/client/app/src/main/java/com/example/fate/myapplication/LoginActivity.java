package com.example.fate.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by fate on 2016/10/27.
 * 注册/登录界面
 */
public class LoginActivity extends Activity {
    EditText user_name;
    EditText user_password;
    Button signIn;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user_name = (EditText) findViewById(R.id.user_name);
        user_password = (EditText) findViewById(R.id.user_password);
        signIn = (Button) findViewById(R.id.signIn);
        login = (Button) findViewById(R.id.login);

        //登录
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "用户信息错误，请重新登录!";
                String url = "http://192.168.1.84:8080/demo/LoginServlet";
                sendUser(msg, url);
            }
        });

        //注册
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = "用户名已被注册，请重新注册!";
                String url = "http://192.168.1.84:8080/demo/SignInServlet";
                sendUser(msg, url);
            }
        });
    }

    public void sendUser(String msg, String url) {
        final String msg1 = msg;
        OkHttpUtils
                .post()
                .url(url)
                .addParams("user_name", user_name.getText().toString())
                .addParams("user_password", user_password.getText().toString())
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e) {}
                    @Override
                    public void onResponse(String response) {
                        String flag=null;
                        String user_id=null;
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            flag=jsonObject.getString("flag");
                            user_id=jsonObject.getString("user_id");
                        }catch (Exception e){}
                        if(flag.equals("false")){
                            Toast.makeText(LoginActivity.this,msg1,Toast.LENGTH_SHORT).show();
                        }
                        if(flag.equals("true")){
                            SharedPreferences preferences=getSharedPreferences("user",MODE_PRIVATE);
                            SharedPreferences.Editor editor=preferences.edit();
                            editor.putString("user_id",user_id);
                            editor.putString("user_name",user_name.getText().toString());
                            editor.putString("user_password",user_password.getText().toString());
                            editor.commit();
                            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);
                        }
                    }
                });
    }
}
