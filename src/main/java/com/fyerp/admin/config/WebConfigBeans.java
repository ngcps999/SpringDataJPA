/*
 * 作者：xuda
 * 创建时间：18-5-2 下午5:21
 * 模块名称：admin
 */

package com.fyerp.admin.config;

import com.fyerp.admin.utils.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * 日期转换器
 */
@Configuration
public class WebConfigBeans {

    @Autowired
    private RequestMappingHandlerAdapter handlerAdapter;

    @PostConstruct
    public void initEditableValidation(){
        ConfigurableWebBindingInitializer initializer = (ConfigurableWebBindingInitializer) handlerAdapter.getWebBindingInitializer();
        if (initializer.getConversionService()!= null) {
            GenericConversionService genericConversionService = (GenericConversionService) initializer.getConversionService();
            genericConversionService.addConverter(new StringToDateConverter());
        }

    }
}
