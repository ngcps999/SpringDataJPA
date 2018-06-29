package com.fyerp.admin.utils;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.domain.SecurityUser;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.enums.ResultEnum;
import com.fyerp.admin.exception.UserException;
import com.fyerp.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component("fyUserDetailService")
public class FyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(FyUserDetailService.class);

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println("loginProcess!");
        User user = userService.findByUsername(s);
        if(user != null && (user.getStrategy() == null
                ||  user.getStrategy().intValue() != Constants.STRATEGY_DELETE)){
            Set<Role> roleSet = user.getRoles();
            Set<GrantedAuthority> auths = new HashSet<>();
            for(Role role : roleSet){
                Set<Permission> permissionSet = role.getPermissions();
                for(Permission permission : permissionSet){
                    if(permission != null && permission.getPermission() != null
                            && permission.getStrategy() != Constants.STRATEGY_DELETE){
                        auths.add(new SimpleGrantedAuthority(permission.getPermission()));
                    }
                }
            }
            return new SecurityUser(s,user.getPassword(), auths);
        }else{
            throw new UserException(ResultEnum.LOGIN_USERNAME_FAIL);
        }


    }


}
