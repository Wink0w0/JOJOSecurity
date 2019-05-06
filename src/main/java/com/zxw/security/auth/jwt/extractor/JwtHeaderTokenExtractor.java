package com.zxw.security.auth.jwt.extractor;

import org.springframework.stereotype.Component;

/**
 *  * An implementation of {@link TokenExtractor} extracts token from
 * Authorization: Bearer scheme.
 * @Author zhangxw
 * @Description:
 * @Date Created in 2019/4/29  11:25.
 * @Modified by:
 */
@Component
public class JwtHeaderTokenExtractor  implements TokenExtractor {
    @Override
    public String extract(String payload) {
        return null;
    }
}
