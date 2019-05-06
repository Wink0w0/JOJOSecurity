package com.zxw.security.model;

/**
 * @Author zhangxw
 * @Description:
 * @Date Created in 2019/4/29  9:44.
 * @Modified by:
 */
public enum Scopes {
    REFRESH_TOKEN;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
