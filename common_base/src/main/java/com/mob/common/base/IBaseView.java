package com.mob.common.base;


public interface IBaseView {

    void showLoadingView();

    void hideLoadingView();

    void hideRefreshView();

    void showToast(String msg);

    void showErrorToast(String msg);

}