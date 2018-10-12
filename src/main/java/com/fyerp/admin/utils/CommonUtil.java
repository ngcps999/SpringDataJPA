/*
 * 作者：xuda
 * 创建时间：18-7-5 上午11:07
 * 模块名称：admin
 */

package com.fyerp.admin.utils;/**
 * com.spring.util
 * jacky
 * 17-12-27
 **/

import java.util.UUID;

/**
 *
 * @author jackycheng
 *
 * @date 2017-12-27-上午10:14
 *
 */

public class CommonUtil {

    /**
     * 生成唯一UUID
     * @return
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
