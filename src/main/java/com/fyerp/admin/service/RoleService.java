/*
 * 作者：xuda
 * 创建时间：18-4-26 下午3:46
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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
    Page<Role> findAll(Pageable pageable);

    /**
     * 查询所有角色
     *
     * @return
     */
    List<Role> findAll();
    List<Role> findAll(List<Long> roleIds);

    List<Role> findAll(Sort sort);
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
