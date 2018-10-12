/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:03
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.domain.Role;
import com.fyerp.admin.respository.RoleRespository;
import com.fyerp.admin.service.RoleService;
import com.fyerp.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRespository roleRespository;

    @Autowired
    private UserService userService;

    @Override
    public Role findOne(Long roleId) {
        return roleRespository.findOne(roleId);
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleRespository.findAll(pageable);
    }

    @Override
    public List<Role> findAll() {
        return roleRespository.findAll();
    }

    @Override
    public List<Role> findAll(Set<Long> roleIds) {
        return roleRespository.findAll(roleIds);
    }

    @Override
    public List<Role> findAll(Sort sort) {
        return roleRespository.findAll(sort);
    }

    @Override
    public Role save(Role role) {
        return roleRespository.save(role);
    }

    @Override
    public void delete(Long roleId) {
        Role role = this.findOne(roleId);
        role.setPermissions(null);
        roleRespository.save(role);
        roleRespository.deleteUserRoleByRoleId(roleId);
        roleRespository.delete(roleId);
    }
}
