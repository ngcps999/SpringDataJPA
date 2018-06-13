/*
 * 作者：xuda
 * 创建时间：18-4-26 下午3:44
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Permission;
import com.fyerp.admin.respository.BaseRespository.BaseRepository;

public interface PermissionRespository extends BaseRepository<Permission,Long> {

//    @Query("select p from user u LEFT JOIN user_role ur on u.user_id = ur.user_id" +
//            "left join Role r on ur.role_id = r.role_id" +
//            "left join role_permission rp on r.role_id = rp.role_id" +
//            "left join permission p on rp.permission_id = p.permission_id" +
//            "where u.user_id =?1")

}
