package com.mob.user.ui;

import android.content.Intent;
import android.os.Bundle;

import com.mob.common.base.BaseActivity;
import com.mob.user.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobUserMainActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_mob_user_main;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }


    @OnClick(R.id.btn_login)
    public void onClick() {
        Intent intent = new Intent(this, MobLoginActivity.class);
        startActivity(intent);
    }
}
