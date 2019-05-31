package com.haoruigang.cniao5play.bean;

import java.io.Serializable;

import io.reactivex.disposables.Disposable;
import lombok.Data;

/**
 * 菜鸟窝http://www.cniao5.com 一个高端的互联网技能学习平台
 *
 * @author Ivan
 * @version V1.0
 * @Package com.cniao5.cniao5market.data.model
 * @Description: ${TODO}(用一句话描述该文件做什么)
 * @date
 */
@Data
public class AppDownloadInfo implements Serializable {


    /**
     * thumbnail : http://t1.market.xiaomi.com/thumbnail/
     * releaseKeyHash : be910af39a26a4a992c6fd01a143ed19
     * icon : AppStore/072725ca573700292b92e636ec126f51ba4429a50
     * apkHash : 010d7c92640b2e994839a81589d83bfa
     * appendExpansionPackSize : 0
     * hdIcon : {"main":"AppStore/07750d40a68e2445a3439a8f781083c431bfa5934"}
     * mainExpansionPackSize : 0
     * channelApkId : -1
     * fitness : 0
     * gamePackSize : 0
     * host : http://f6.market.xiaomi.com/download/
     * diffFileSize : 0
     * apkSize : 40309436
     * id : 1359
     * apk : AppStore/07650c4f6a86443a03920b69d83268aec54f00f5d
     * refPosition : -1
     */

    private String thumbnail;
    private String releaseKeyHash;
    private String icon;
    private String apkHash;
    private int appendExpansionPackSize;
    private HdIconEntity hdIcon;
    private int mainExpansionPackSize;
    private int channelApkId;
    private int fitness;
    private int gamePackSize;
    private String host;
    private int diffFileSize;
    private int apkSize;
    private int id;
    private String apk;
    private int refPosition;

    private Disposable mDisposable;


    private String downloadUrl;

    public String getDownloadUrl() {
        if (downloadUrl != null)
            return downloadUrl;
        return this.getHost() + this.getApk();
    }

    public String getHost() {
        return host;
    }

    public String getApk() {
        return apk;
    }

    @Data
    public class HdIconEntity implements Serializable {
        /**
         * main : AppStore/07750d40a68e2445a3439a8f781083c431bfa5934
         */
        private String main;

    }

}

