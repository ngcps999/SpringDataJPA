/*
 * 作者：xuda
 * 创建时间：18-5-3 上午11:20
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.User;

public interface LoginService {

    public boolean verifyLogin(User user);
}
