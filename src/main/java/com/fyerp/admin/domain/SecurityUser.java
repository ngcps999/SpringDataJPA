package com.fyerp.admin.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Set;

@Data
public class SecurityUser extends User {

    public Set<Role> userRoles;

    public Long userId;

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities,Set roles,Long userId) {
        super(username, password, authorities);
        this.userRoles = roles;
        this.userId = userId;
    }
}
