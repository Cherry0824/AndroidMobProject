package com.mob.common.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Administrator on 2019/2/28.
 */

public abstract class BaseMvpFragment<P extends BasePresenter> extends BaseFragment implements IBaseView{
    protected P mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (initPresenter() != null) {
            mPresenter = initPresenter();
            mPresenter.attachView(this);
        }
    }
    public abstract P initPresenter();
    @Override
    public void showLoadingView() {

    }

    @Override
    public void hideLoadingView() {

    }

    @Override
    public void hideRefreshView() {

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null && mPresenter.isViewAttached()) {
            mPresenter.destroy();
            mPresenter = null;
        }

    }
}
