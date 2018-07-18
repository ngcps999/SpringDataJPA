package com.fyerp.admin.config.security;

import com.alibaba.fastjson.JSONObject;
import com.fyerp.admin.domain.Result;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.utils.Constants;
import com.fyerp.admin.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private Logger log = LoggerFactory.getLogger(FyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        log.info(Constants.LOGIN_SUCCESS);
        httpServletResponse.setContentType("application/json;charset=UTF-8"); // 响应类型
        Result result = ResultUtil.success(authentication);
        httpServletResponse.getWriter().write(JSONObject.toJSONString(result)); // 数据转化成json类型后再进行响应操作
    }
}
