package com.example.fate.myapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // 底部菜单 4个Linearlayout
    private LinearLayout ll_home;
    private LinearLayout ll_post;
    private LinearLayout ll_comment;
    private LinearLayout ll_setting;

    // 底部菜单 4个ImageView
    private ImageView iv_home;
    private ImageView iv_post;
    private ImageView iv_comment;
    private ImageView iv_setting;

    // 底部菜单 4个菜单标题
    private TextView tv_home;
    private TextView tv_post;
    private TextView tv_comment;
    private TextView tv_setting;

    //  4个Fragment
    private Fragment HomeFragment;
    private Fragment PostFragment;
    private Fragment CommentFragment;
    private Fragment SettingFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初始化控件
        initView();
        // 初始化底部按钮事件
        initEvent();
        // 初始化并设置当前Fragment
        initFragment(0);
    }

    private void initFragment(int index) {
        // 由于是引用了V4包下的Fragment，所以这里的管理器要用getSupportFragmentManager获取
        // 开启事务
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // 隐藏所有Fragment
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (PostFragment == null) {
                    PostFragment = new PostFragment();
                    transaction.add(R.id.main_content,PostFragment);
                } else {
                    transaction.show(PostFragment);
                }
                break;
            case 1:
                if (HomeFragment == null) {
                    HomeFragment = new HomeFragment();
                    transaction.add(R.id.main_content, HomeFragment);
                } else {
                    transaction.show(HomeFragment);
                }
                break;
            case 2:
                if (CommentFragment == null) {
                    CommentFragment = new CommentFragment();
                    transaction.add(R.id.main_content, CommentFragment);
                } else {
                    transaction.show(CommentFragment);
                }
                break;
            case 3:
                if (SettingFragment == null) {
                    SettingFragment = new SettingFragment();
                    transaction.add(R.id.main_content, SettingFragment);
                } else {
                    transaction.show(SettingFragment);
                }
                break;
            default:
                break;
        }
        // 提交事务
        transaction.commit();
    }

    //隐藏Fragment
    private void hideFragment(FragmentTransaction transaction) {
        if (PostFragment != null) {
            transaction.hide(PostFragment);
        }
        if (HomeFragment != null) {
            transaction.hide(HomeFragment);
        }
        if (CommentFragment != null) {
            transaction.hide(CommentFragment);
        }
        if (SettingFragment != null) {
            transaction.hide(SettingFragment);
        }
    }

    private void initEvent() {
        // 设置按钮监听
        ll_home.setOnClickListener(this);
        ll_post.setOnClickListener(this);
        ll_comment.setOnClickListener(this);
        ll_setting.setOnClickListener(this);
    }

    private void initView() {
        // 底部菜单 个Linearlayout
        this.ll_home = (LinearLayout) findViewById(R.id.ll_home);
        this.ll_post = (LinearLayout) findViewById(R.id.ll_post);
        this.ll_comment = (LinearLayout) findViewById(R.id.ll_comment);
        this.ll_setting = (LinearLayout) findViewById(R.id.ll_setting);

        // 底部菜单 个ImageView
        this.iv_home = (ImageView) findViewById(R.id.iv_home);
        this.iv_post = (ImageView) findViewById(R.id.iv_post);
        this.iv_comment = (ImageView) findViewById(R.id.iv_comment);
        this.iv_setting = (ImageView) findViewById(R.id.iv_setting);

        // 底部菜单 个菜单标题
        this.tv_home = (TextView) findViewById(R.id.tv_home);
        this.tv_post = (TextView) findViewById(R.id.tv_post);
        this.tv_comment = (TextView) findViewById(R.id.tv_comment);
        this.tv_setting = (TextView) findViewById(R.id.tv_setting);
    }

    @Override
    public void onClick(View v) {
        // 在每次点击后将所有的底部按钮(ImageView,TextView)颜色改为灰色，然后根据点击着色
        restartBotton();
        // ImageView和TetxView置为绿色，页面随之跳转
        switch (v.getId()) {
            case R.id.ll_home:
                iv_home.setImageResource(R.drawable.liebiao);
                tv_home.setTextColor(0xff1B940A);
                initFragment(0);
                break;
            case R.id.ll_post:
                iv_post.setImageResource(R.drawable.daohang);
                tv_post.setTextColor(0xff1B940A);
                initFragment(1);
                break;
            case R.id.ll_comment:
                iv_comment.setImageResource(R.drawable.pinglun);
                tv_comment.setTextColor(0xff1B940A);
                initFragment(2);
                break;
            case R.id.ll_setting:
                iv_setting.setImageResource(R.drawable.shezhi);
                tv_setting.setTextColor(0xff1B940A);
                initFragment(3);
                break;
            default:
                break;
        }
    }

    private void restartBotton() {
        // ImageView置为灰色
//        iv_home.setImageResource(R.drawable.tab_weixin_normal);
//        iv_address.setImageResource(R.drawable.tab_address_normal);
//        iv_friend.setImageResource(R.drawable.tab_find_frd_normal);
//        iv_setting.setImageResource(R.drawable.tab_settings_normal);
        // TextView置为白色
        tv_home.setTextColor(0xffffffff);
        tv_post.setTextColor(0xffffffff);
        tv_comment.setTextColor(0xffffffff);
        tv_setting.setTextColor(0xffffffff);
    }
}