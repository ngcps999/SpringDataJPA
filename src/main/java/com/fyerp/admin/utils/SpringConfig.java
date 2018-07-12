/*
 * 作者：xuda
 * 创建时间：18-7-5 上午10:47
 * 模块名称：admin
 */

package com.fyerp.admin.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuda
 * @date 2018-7-5-上午10:49
 *
 * 文件上传配置
 */
@Configuration
@ConfigurationProperties("storage")
public class SpringConfig {
    private String location = "/tmp/files";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
