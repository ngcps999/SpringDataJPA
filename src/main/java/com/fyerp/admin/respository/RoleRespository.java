/*
 * 作者：xuda
 * 创建时间：18-4-26 下午3:43
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Role;
import com.fyerp.admin.respository.BaseRespository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRespository extends BaseRepository<Role,Long> {

    @Modifying
    @Transactional
    @Query(value = "delete from user_role where role_id = ?1",nativeQuery = true)
    void deleteUserRoleByRoleId(Long roleId);

}
