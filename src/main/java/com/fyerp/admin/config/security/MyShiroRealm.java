package com.fyerp.admin.config.security;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.UserException;
import com.fyerp.admin.service.UserService;
import com.fyerp.admin.utils.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User)SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //获取角色
        Set<String> roleSet = new HashSet<String>();
        //获取权限
        Set<String> permissions = new HashSet<String>();

        if(user != null && user.getRoles() != null && user.getRoles().size() > 0){
            for(Role role : user.getRoles()){
                roleSet.add(role.getRole());
                if(role.getPermissions() != null && role.getPermissions().size() > 0){
                    for(Permission permission : role.getPermissions()){
                        permissions.add(permission.getPermission());
                    }
                }
            }
        }
        info.setRoles(roleSet);
        info.setStringPermissions(permissions);

        return info;
    }

    /**
     * 验证身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        User user = userService.findByUsername(userName);
        if(user == null){
            throw new UserException(ResultEnum.LOGIN_FAILED);
        }else if(user.getStrategy()!= null && user.getStrategy() == Constants.STRATEGY_DELETE){
            throw new UserException(ResultEnum.USER_STRATEGY_DELETE);
        }
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
