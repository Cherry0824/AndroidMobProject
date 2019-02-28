package com.mob.main.ui;

import android.os.Bundle;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mob.common.base.BaseActivity;
import com.mob.common.constants.ARouterConfig;
import com.mob.main.R;
import com.mob.main.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WelcomeActivity extends BaseActivity {


    @BindView(R2.id.btn_login)
    Button btnLogin;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initViews(Bundle savedInstanceState) {

    }


    @OnClick(R2.id.btn_login)
    public void onClick() {
        ARouter.getInstance().build(ARouterConfig.USER_LOGIN).navigation();
    }
}
