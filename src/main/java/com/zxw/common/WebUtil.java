package com.zxw.common;

import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.http.HttpServletRequest;


/**
 * Created by Administrator on 2019-4-21.
 * 对于Http请求判断是否合符规范
 */
public class WebUtil {
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";

    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private static final String CONTENT_TYPE = "Content-type";

    private static final String CONTENT_TYPE_JSON="application/json";

    //判断是否为ajxa请求
    public static boolean isAjax(HttpServletRequest httpServletRequest){
      return XML_HTTP_REQUEST.equals(httpServletRequest.getHeader(X_REQUESTED_WITH));
    }
    //重载该方法
    public static boolean isAjxa(SavedRequest savedRequest){
        return  savedRequest.getHeaderValues(X_REQUESTED_WITH).contains(X_REQUESTED_WITH);
    }
    public static boolean isContentTypeJson(SavedRequest request) {
        return request.getHeaderValues(CONTENT_TYPE).contains(CONTENT_TYPE_JSON);
    }
}
