package com.zxw.profile.endpoint;


import com.zxw.security.auth.JwtAuthenticationToken;
import com.zxw.security.model.UserContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangxw
 * @Description:
 * @Date Created in 2019/4/29  16:00.
 * @Modified by:
 */
@RestController
public class ProfileEndpoint {
    @RequestMapping(value="/api/me", method= RequestMethod.GET)
    public @ResponseBody
    UserContext get(JwtAuthenticationToken token) {
        return (UserContext) token.getPrincipal();
    }
}
