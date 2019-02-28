package com.mob.common.http;


import com.mob.common.R;
import com.mob.common.base.BaseApplication;
import com.mob.common.base.IBaseView;
import com.mob.common.http.exception.ResponseException;

import io.reactivex.observers.DisposableObserver;

public abstract class NetDisposableObserver<T> extends DisposableObserver<T> {

    private IBaseView mBaseMvpView;
    private boolean isShowLoadingView;

    public NetDisposableObserver(IBaseView baseMvpView) {
        this.mBaseMvpView = baseMvpView;
        this.isShowLoadingView = true;
    }

    public NetDisposableObserver(IBaseView baseMvpView, boolean isShowLoadingView) {
        this.mBaseMvpView = baseMvpView;
        this.isShowLoadingView = isShowLoadingView;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.mBaseMvpView != null && this.isShowLoadingView) {
            this.mBaseMvpView.showLoadingView();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        if (this.mBaseMvpView != null) {
            this.mBaseMvpView.hideLoadingView();
            this.mBaseMvpView.hideRefreshView();
            if (throwable instanceof ResponseException) {
                ResponseException responseException = (ResponseException) throwable;
                if (responseException.getStatus() != 200) {
                    this.mBaseMvpView.showToast(responseException.getMessage());
                } else {
                    this.mBaseMvpView.showToast(BaseApplication.getInstances().getResources().getString(R.string.app_name));
                }
            }
        }
    }

    @Override
    public void onComplete() {
        if (this.mBaseMvpView != null) {
            this.mBaseMvpView.hideLoadingView();
            this.mBaseMvpView.hideRefreshView();
        }
    }
}
