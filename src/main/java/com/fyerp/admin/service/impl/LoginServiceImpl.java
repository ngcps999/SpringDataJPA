/*
 * 作者：xuda
 * 创建时间：18-5-3 上午11:20
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.User;
import com.fyerp.admin.respository.LoginRespository;
import com.fyerp.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginRespository loginRespository;
    @Override
    public boolean verifyLogin(User user) {
        List<User> usernameAndPassword = loginRespository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        return usernameAndPassword.size()>0;
    }
}
