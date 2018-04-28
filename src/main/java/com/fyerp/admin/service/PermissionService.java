/*
 * 作者：xuda
 * 创建时间：18-4-28 上午11:07
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Permission;

import java.util.List;

public interface PermissionService  {

    /**
     * 查询单个角色
     * @param permissionId
     * @return
     */
    Permission findOne(Long permissionId);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Permission> findAll();

    /**
     * 新增/更新角色
     *
     * @param permission
     * @return
     */
    Permission save(Permission permission);

    /**
     * 删除角色
     *
     * @param permissionId
     */
    void delete(Long permissionId);
}
