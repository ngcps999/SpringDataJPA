package com.fyerp.admin.config.security;

import com.alibaba.fastjson.JSONObject;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.utils.Constants;
import com.fyerp.admin.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class FyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private Logger log = LoggerFactory.getLogger(FyAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        log.info(Constants.LOGIN_FAILURE);
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(ResultUtil.error(ResultEnum.LOGIN_FAILED)));
    }
}
