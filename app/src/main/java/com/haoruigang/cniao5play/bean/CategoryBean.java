package com.haoruigang.cniao5play.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class CategoryBean implements Serializable {

    /**
     * setName : true
     * addTime : 0
     * setAddTime : false
     * setIcon192 : false
     * topAppId : 48217
     * icon : AppStore/816d41df-4a7a-40a5-84ff-0ff536b6cbbf
     * iconForPad :
     * setDescription : false
     * hdIcon : {}
     * categoryenum : 1
     * setTopAppIdForPad : true
     * setIcon136 : false
     * hdIconSize : 0
     * setId : true
     * setOperator : false
     * id : 15
     * setTopAppId : true
     * topAppIdForPad : 49776
     * setCategoryenum : true
     * setIconForPad : true
     * updateTime : 1398650464153
     * priority : 10
     * parentId : 0
     * setStatus : false
     * appQuantity : 0
     * setIcon : true
     * setAppQuantity : false
     * setIcon224 : false
     * setIcon90 : false
     * setIcon168 : false
     * name : Games
     * setParentId : true
     * setUpdateTime : true
     * setPriority : true
     * setHdIcon : true
     * status : 0
     */

    private boolean setName;
    private int addTime;
    private boolean setAddTime;
    private boolean setIcon192;
    private int topAppId;
    private String icon;
    private String iconForPad;
    private boolean setDescription;
    private HdIconBean hdIcon;
    private int categoryenum;
    private boolean setTopAppIdForPad;
    private boolean setIcon136;
    private int hdIconSize;
    private boolean setId;
    private boolean setOperator;
    private int id;
    private boolean setTopAppId;
    private int topAppIdForPad;
    private boolean setCategoryenum;
    private boolean setIconForPad;
    private long updateTime;
    private int priority;
    private int parentId;
    private boolean setStatus;
    private int appQuantity;
    private boolean setIcon;
    private boolean setAppQuantity;
    private boolean setIcon224;
    private boolean setIcon90;
    private boolean setIcon168;
    private String name;
    private boolean setParentId;
    private boolean setUpdateTime;
    private boolean setPriority;
    private boolean setHdIcon;
    private int status;

    @Data
    private static class HdIconBean implements Serializable {
    }
}
