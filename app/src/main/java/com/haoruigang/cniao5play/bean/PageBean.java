package com.haoruigang.cniao5play.bean;

import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageBean<T> extends BaseBean {


    /**
     * hasMore : false
     * datas : []
     * status : 1
     * message : success
     */

    private boolean hasMore;
    private List<T> datas;


}
