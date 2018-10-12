/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:10
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface DepartmentService {

    Department findOne(Long departmentId);

    DepartmentDTO findOneDTO(Long departmentId);

    List<Department> findAll();

    List<DepartmentDTO> findAllDTO();

    Page<Department> findAll(Pageable pageable);

    Page<DepartmentDTO> findAllDTO(Pageable pageable);

    List<Department> findAll(Sort sort);

    List<DepartmentDTO> findAllDTO(Sort sort);

    Department save(Department department);
    DepartmentDTO saveDTO(Department department);

    Department saveAndFlush(Department department);

    List<Department> save(List<Department> departments);

    void delete(Long departmentId);

    public Integer update(Long departmentId, Long userId);
    public Integer insert(Long departmentId, Long userId);

    public Integer deleteA(Long departmentId,Long userId);

    Department updateDepartment(Department department);
}
