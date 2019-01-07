package com.haoruigang.cniao5play.bean;

import java.util.List;

import lombok.Data;

@Data
public class IndexBean {

    private List<Banner> banners;
    private List<AppInfoBean> recommendApps;
    private List<AppInfoBean> recommendGames;

    @Data
    public class Banner {

        /**
         * thumbnail : http://t4.market.mi-img.com/thumbnail/jpeg/l750/AppStore/0ff69546de24355c8a484aafc27222e230f41f76f
         * action : subject
         * id : 169136
         */

        private String thumbnail;
        private String action;
        private String id;

    }

}
