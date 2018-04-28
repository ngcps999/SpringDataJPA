/*
 * 作者：xuda
 * 创建时间：18-4-26 下午3:46
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询单个角色
     * @param roleId
     * @return
     */
    Role findOne(Long roleId);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 新增/更新角色
     *
     * @param role
     * @return
     */
    Role save(Role role);

    /**
     * 删除角色
     *
     * @param roleId
     */
    void delete(Long roleId);

    
}
