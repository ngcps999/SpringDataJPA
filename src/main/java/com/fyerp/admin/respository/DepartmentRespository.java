/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:10
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRespository extends JpaRepository<Department,Long> {
}
