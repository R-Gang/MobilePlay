package com.haoruigang.cniao5play.bean;

import java.util.List;

import lombok.Data;

@Data
public class PageBean<T> {


    /**
     * hasMore : false
     * datas : []
     * status : 1
     * message : success
     */

    private boolean hasMore;
    private List<T> datas;
    private int status;
    private String message;


}
