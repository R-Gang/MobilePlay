package com.haoruigang.cniao5play.bean;

import java.io.Serializable;

import lombok.Data;

/**
 * 1.Http请求结果预处理
 *
 * @param <T>
 */
@Data
public class BaseBean<T> implements Serializable {

    private static final int SUCCESS = 1;

    private int status;
    private String message;
    private T data;

    public boolean success() {
        return (status == SUCCESS);
    }

}
