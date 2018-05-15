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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRespository departmentRespository;

    @Override
    public Department findOne(Long departmentId) {
        return departmentRespository.findOne(departmentId);
    }

    @Override
    public List<Department> findAll() {
        return departmentRespository.findAll();
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRespository.findAll(pageable);
    }

    @Override
    public List<Department> findAll(Sort sort) {
        return departmentRespository.findAll(sort);
    }

    @Override
    public Department save(Department department) {
        return departmentRespository.saveAndFlush(department);
    }

    @Override
    public void delete(Long departmentId) {
        departmentRespository.delete(departmentId);
    }
}
