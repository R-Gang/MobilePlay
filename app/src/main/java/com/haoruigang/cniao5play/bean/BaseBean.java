package com.haoruigang.cniao5play.bean;

import java.io.Serializable;

/**
 * 1.Http请求结果预处理
 *
 * @param <T>
 */
public class BaseBean<T> implements Serializable {

    private static final int SUCCESS = 1;

    private int status;
    private String message;
    private T datas;

    public boolean success() {
        return (status == SUCCESS);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getDatas() {
        return datas;
    }

    public void setDatas(T datas) {
        this.datas = datas;
    }

}
