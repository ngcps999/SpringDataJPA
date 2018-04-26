/*
 * 作者：xuda
 * 创建时间：18-4-26 下午3:44
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRespository extends JpaRepository<Permission,Long> {
}
