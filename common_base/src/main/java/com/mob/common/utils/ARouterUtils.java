package com.mob.common.utils;

import com.alibaba.android.arouter.launcher.ARouter;
import com.mob.common.base.BaseActivity;
import com.mob.common.base.BaseFragment;

/**
 * Created by Administrator on 2019/2/27.
 */

public class ARouterUtils {
    /**
     * 根据path返回Fragment
     *
     * @param path path
     * @return fragment
     */
    public static BaseFragment getFragment(String path) {
        return (BaseFragment) ARouter.getInstance()
                .build(path)
                .navigation();
    }

    /**
     * 根据path返回Activity
     *
     * @param path path
     * @return Activity
     */
    public static BaseActivity getActivity(String path) {
        return (BaseActivity) ARouter.getInstance()
                .build(path)
                .navigation();
    }
}
