/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:10
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface DepartmentRespository extends JpaRepository<Department, Long> {

    @Modifying
    @Transactional
    @Query(value = "update department inner join department_user user2 on department.department_id = user2.department_id set user2.user_id = ?2 where user2.department_id =?1", nativeQuery = true)
    public Integer update(@RequestParam("department_id") Long departmentId, @RequestParam("user_id") Long userId);


    @Transactional
    @Query(value = "insert into department_user values (?1,?2)",nativeQuery = true)
    public Integer insert(@RequestParam("department_id") Long departmentId, @RequestParam("user_id") Long userId);

    @Transactional
    @Query(value = "delete from department_user where user_id=?1",nativeQuery = true)
    public Integer deleteA(@RequestParam("user_id") Long userId);


}
