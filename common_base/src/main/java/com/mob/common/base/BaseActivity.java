package com.mob.common.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.view.WindowManager;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.mob.common.R;
import com.mob.common.bean.MessageEvent;
import com.mob.common.manager.ActivityStack;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Lenovo on 2019/1/15.
 */

public abstract class BaseActivity extends FragmentActivity {
    private Unbinder unbinder;
    private ViewStub emptyView;
    protected ImmersionBar immersionBar;
    protected ActivityStack activityManage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);//软键盘与处输入框
        setContentView(getLayoutRes());
        unbinder = ButterKnife.bind(this);
        immersionBar = ImmersionBar.with(this);
        immersionBar.statusBarColor(initImmersionBarColor());
        immersionBar.init();
        activityManage = ActivityStack.getInstance();
        activityManage.addActivity(this);
        if (regEvent()) {
            EventBus.getDefault().register(this);
        }
    }

    protected int initImmersionBarColor() {
        return R.color.red;
    }

    protected boolean regEvent() {
        return false;
    }

    public abstract int getLayoutRes();

    public abstract void initViews(Bundle savedInstanceState);

    /**
     * 子类接受事件 重写该方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(MessageEvent messageEvent) {
    }

    //***************************************空页面方法*************************************
    protected void showEmptyView(String text) {
        showEmptyOrErrorView(text, R.drawable.base_no_data);
    }

    protected void showEmptyView() {
        showEmptyView(getString(R.string.no_data));
    }

    protected void showErrorView(String text) {
        showEmptyOrErrorView(text, R.drawable.base_net_error);
    }

    protected void showErrorView() {
        showErrorView(getString(R.string.error_data));
    }

    public void showEmptyOrErrorView(String text, int img) {

        if (emptyView == null) {
            emptyView = findViewById(R.id.empty_view);
        }
        emptyView.setVisibility(View.VISIBLE);
        findViewById(R.id.empty_image).setBackgroundResource(img);
        ((TextView) findViewById(R.id.empty_text)).setText(text);
        findViewById(R.id.empty_content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPageClick();
            }
        });
    }

    protected void hideEmptyView() {
        if (emptyView != null) {
            emptyView.setVisibility(View.GONE);
        }
    }

    /**
     * 空页面被点击
     */
    protected void onPageClick() {

    }

    //***************************************空页面方法*********************************

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (regEvent()) {
            EventBus.getDefault().unregister(this);
        }
        //必须调用该方法，防止内存泄漏
        if (immersionBar != null) {
            immersionBar.destroy();
        }
        activityManage.finishActivity(this);
    }
}
