/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:10
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    Page<Department> findAll(Pageable pageable);

    Department save(Department department);

    void delete(Long departmentId);
}
