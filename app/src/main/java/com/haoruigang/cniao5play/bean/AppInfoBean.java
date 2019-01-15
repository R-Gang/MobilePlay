package com.haoruigang.cniao5play.bean;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AppInfoBean extends PageBean {


    /**
     * addTime : 0
     * hasSameDevApp : false
     * videoId : 0
     * source :
     * versionName : 0.4.35
     * ratingScore : 3
     * briefShow : 自由交易变土豪
     * developerId : 0
     * fitness : 0
     * id : 598318
     * level1CategoryId : 15
     * releaseKeyHash : 240bf36f3925b5245f226a9dd033d112
     * relateAppHasMore : false
     * rId : 0
     * suitableType : 2
     * briefUseIntro : false
     * ads : 0
     * publisherName : 4399
     * level2CategoryId : 19
     * position : 0
     * favorite : false
     * isFavorite : false
     * appendSize : 0
     * level1CategoryName : 游戏
     * samDevAppHasMore : false
     * displayName : 王者修仙
     * icon : AppStore/0d1b956b1088528b4d5c7abd962c98fa93641fc36
     * screenshot : AppStore/09ab375b50a224a3a2ac1747470391e28a339d349,AppStore/05667443217de469d2860761eda79eb6e8705a9a0,AppStore/0a667443217de469d2860261ed979eb2e870ea9a0,AppStore/03da7658cf33f48de2f52de00758cf412a46c406b,AppStore/0d4594c1dc5c4350c9a7e7e7fc03bcaf70f431de3
     * ratingTotalCount : 0
     * adType : 0
     * apkSize : 59916094
     * packageName : com.yjxx.union.mi
     * updateTime : 1535364625307
     * grantCode : 0
     * versionCode : 435
     * appTags : [{"tagId":258,"link":"sametag/258","tagName":"3D"},{"tagId":262,"link":"sametag/262","tagName":"魔幻"},{"tagId":278,"link":"sametag/278","tagName":"角色"},{"tagId":350,"link":"sametag/350","tagName":"3D RPG"},{"tagId":478,"link":"sametag/478","tagName":"角色扮演"},{"tagId":1438,"link":"sametag/1438","tagName":"MMORPG"}]
     * diffFileSize : 0
     */

    private int addTime;
    private boolean hasSameDevApp;
    private int videoId;
    private HdIcon hdIcon;
    private String source;
    private String versionName;
    private Float ratingScore;
    private String briefShow;
    private int developerId;
    private int fitness;
    private int id;
    private int level1CategoryId;
    private String releaseKeyHash;
    private boolean relateAppHasMore;
    private int rId;
    private int suitableType;
    private boolean briefUseIntro;
    private int ads;
    private String publisherName;
    private int level2CategoryId;
    private int position;
    private boolean favorite;
    private boolean isFavorite;
    private int appendSize;
    private List<AppInfoBean> relateAppInfoList;
    private String level1CategoryName;
    private boolean samDevAppHasMore;
    private String displayName;
    private String icon;
    private String changeLog;
    private String screenshot;
    private String permissionIds;
    private int ratingTotalCount;
    private int adType;
    private String web;
    private int apkSize;
    private String packageName;
    private String introduction;
    private long updateTime;
    private int grantCode;
    private String detailHeaderImage;
    private int versionCode;
    private List<AppTagsBean> appTags;
    private int diffFileSize;
    private List<AppInfoBean> sameDevAppInfoList;
    private String categoryId;

    @Data
    private static class AppTagsBean implements Serializable {
        /**
         * tagId : 258
         * link : sametag/258
         * tagName : 3D
         */

        private int tagId;
        private String link;
        private String tagName;

    }

    @Data
    private static class HdIcon implements Serializable {
        /**
         * main:AppStore/08888245de1fa4a8115cfe21f2c4c05d1ca74c144
         */

        private String main;

    }


}
