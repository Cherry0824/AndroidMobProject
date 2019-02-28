package com.mob.common.http.factory;


import com.mob.common.http.RetrofitConfig;

public class ApiFactory {

    public static <T> T create(Class<T> paramClass) {
        return RetrofitConfig.getInstance().getRetrofit("").create(paramClass);
    }

}
