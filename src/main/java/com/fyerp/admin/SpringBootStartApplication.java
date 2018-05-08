/*
 * 作者：xuda
 * 创建时间：18-5-8 上午8:52
 * 模块名称：admin
 */

package com.fyerp.admin;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class SpringBootStartApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(AdminApplication.class);
    }
}
