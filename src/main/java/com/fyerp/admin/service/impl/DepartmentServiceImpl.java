/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:12
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.respository.DepartmentRespository;
import com.fyerp.admin.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRespository departmentRespository;

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRespository.findAll(pageable);
    }

    @Override
    public Department save(Department department) {
        return departmentRespository.save(department);
    }

    @Override
    public void delete(Long departmentId) {
        departmentRespository.delete(departmentId);
    }
}
