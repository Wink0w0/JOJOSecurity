package com.zxw.security.auth.jwt.verifier;

/**
 * @Author zhangxw
 * @Description:
 * @Date Created in 2019/4/29  13:32.
 * @Modified by:
 */
public interface TokenVerifier {
    boolean verify(String jti);
}
