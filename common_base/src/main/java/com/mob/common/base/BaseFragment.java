package com.mob.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;

import com.mob.common.R;
import com.mob.common.bean.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2019/2/27.
 */

public abstract class BaseFragment extends Fragment {

    private ViewStub emptyView;
    private View rootView;
    private Unbinder unBinder;

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commit();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_base, container, false);
        ((ViewGroup) rootView.findViewById(R.id.fl_content)).addView(getLayoutInflater().inflate(getLayoutId(), null));
        unBinder = ButterKnife.bind(this, rootView);
        if (regEvent()) {
            EventBus.getDefault().register(this);
        }
        return rootView;
    }


    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(savedInstanceState);
    }

    /**
     * 子类接受事件 重写该方法
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventBus(MessageEvent messageEvent) {
    }

    /**
     * 需要接收事件 重新该方法 并返回true
     */
    protected boolean regEvent() {
        return false;
    }

    public abstract int getLayoutId();

    public abstract void initViews(Bundle savedInstanceState);

    //***************************************空页面方法*************************************
    protected void showEmptyView() {
        showEmptyOrErrorView(getString(R.string.no_data), R.drawable.base_no_data);
    }


    protected void showErrorView() {
        showEmptyOrErrorView(getString(R.string.error_data), R.drawable.base_net_error);
    }

    public void showEmptyOrErrorView(String text, int img) {
        if (emptyView == null) {
            emptyView = rootView.findViewById(R.id.empty_view);
        }
        emptyView.setVisibility(View.VISIBLE);
        rootView.findViewById(R.id.empty_image).setBackgroundResource(img);
        ((TextView) rootView.findViewById(R.id.empty_text)).setText(text);
        rootView.findViewById(R.id.empty_content).setOnClickListener(new View.OnClickListener() {
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
    public void onDestroy() {
        super.onDestroy();
        if (unBinder != null) {
            unBinder.unbind();
        }
        if (regEvent()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
