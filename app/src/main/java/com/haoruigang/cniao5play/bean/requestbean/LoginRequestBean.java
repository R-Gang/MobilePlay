package com.haoruigang.cniao5play.bean.requestbean;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginRequestBean {

    private String phone;
    private String password;

}
