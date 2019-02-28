package com.mob.user.ui;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.mob.common.base.BaseActivity;
import com.mob.common.constants.ARouterConfig;
import com.mob.user.R;
import com.mob.user.R2;
import com.mob.user.bean.UserInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@Route(path = ARouterConfig.USER_LOGIN)
public class MobLoginActivity extends BaseActivity {

    UserInfo userInfo;
    @BindView(R2.id.test)
    TextView test;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_mob_login;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {
        userInfo = new UserInfo();

    }

    public void test() {

    }

    @OnClick(R2.id.test)
    public void onClick() {
    }
}
