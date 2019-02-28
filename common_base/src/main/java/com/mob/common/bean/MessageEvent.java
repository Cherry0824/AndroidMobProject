package com.mob.common.bean;

import lombok.Data;

/**
 * Created by Administrator on 2019/2/28.
 */
@Data
public class MessageEvent<T> {
    private int messageCode;
    private String message;
    private T messageData;
    public MessageEvent(int messageCode) {
        this.messageCode = messageCode;
    }

    public MessageEvent(int messageCode, String message) {
        this.messageCode = messageCode;
        this.message = message;
    }

    public MessageEvent(int messageCode, T t) {
        this.messageCode = messageCode;
        this.messageData = t;
    }

    public MessageEvent(int messageCode, String message, T t) {
        this.messageCode = messageCode;
        this.messageData = t;
        this.message = message;
    }
}
