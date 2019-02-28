package com.mob.common.http.exception;


import retrofit2.HttpException;

public class ResponseExceptionHandler {

    public static ResponseException handleResponseException(Throwable throwable) {
        ResponseException responseException = null;
        if (throwable instanceof HttpException) {
            responseException = new ResponseException(throwable, ((HttpException) throwable).code());
            responseException.setMessage(((HttpException) throwable).message());
            return responseException;
        } else if (throwable instanceof ServerException) {
            if (((ServerException) throwable).getStatus() == 1) {  //token失效
                responseException = new ResponseException(throwable, 1);
            } else if (((ServerException) throwable).getStatus() != 200) {
                responseException = new ResponseException(throwable, ((ServerException) throwable).getStatus());
                responseException.setMessage(((ServerException) throwable).getMessage());
            }
            return responseException;//
        } else {
            responseException = new ResponseException(throwable, 1);
            responseException.setMessage(throwable.getMessage());
            return responseException;
        }
    }
}
