///*
// * 作者：xuda
// * 创建时间：18-4-20 上午10:08
// * 模块名称：admin
// */
//
//package com.fyerp.admin.service.impl;
//
//import com.fyerp.admin.domain.Role;
//import com.fyerp.admin.domain.User;
//import com.fyerp.admin.respository.UserRespository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.method.P;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class CustomUserServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRespository userRespository;
//
//    /**
//     * 重写loadUserByUsername 方法获得 userdetails 类型用户
//     * @param username
//     * @return
//     * @throws UsernameNotFoundException
//     */
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//
//        User user = userRespository.findUserByUsername(username);
//        if(user == null){
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
//        for(Role role:user.getRoles())
//        {
//            authorities.add(new SimpleGrantedAuthority(role.getRole()));
//            System.out.println(role.getRole());
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),
//                user.getPassword(), authorities);
//
//    }
//}
