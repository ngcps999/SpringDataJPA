/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:09
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.respository.PermissionRespository;
import com.fyerp.admin.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRespository permissionRespository;

    @Override
    public Permission findOne(Long permissionId) {
        return permissionRespository.findOne(permissionId);
    }

    @Override
    public Page<Permission> findAll(Pageable pageable) {
        return permissionRespository.findAll(pageable);
    }

    @Override
    public List<Permission> findAll() {
        return permissionRespository.findAll();
    }

    @Override
    public List<Permission> findAll(List<Long> permissionIds) {
        return permissionRespository.findAll(permissionIds);
    }

    @Override
    public List<Permission> findAll(Sort sort) {
        return permissionRespository.findAll(sort);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRespository.save(permission);
    }

    @Override
    public void delete(Long permissionId) {
        permissionRespository.delete(permissionId);
    }
}
