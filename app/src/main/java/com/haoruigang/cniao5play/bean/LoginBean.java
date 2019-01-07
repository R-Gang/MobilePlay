package com.haoruigang.cniao5play.bean;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginBean implements Serializable {

    private String token;
    private User user;

    @Data
    public class User implements Serializable {
        private String id;
        private String email;
        private String logo_url;
        private String username;
        private String mobi;

    }
}
