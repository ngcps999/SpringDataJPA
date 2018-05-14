/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:03
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Role;
import com.fyerp.admin.respository.RoleRespository;
import com.fyerp.admin.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRespository roleRespository;

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
    public Role save(Role role) {
        return roleRespository.save(role);
    }

    @Override
    public void delete(Long roleId) {
        roleRespository.delete(roleId);
    }
}
