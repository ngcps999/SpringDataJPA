/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:12
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.respository.DepartmentRespository;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.utils.convert.Department2DepartmentDTOConverter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public DepartmentDTO findOneDTO(Long departmentId) {

        Department department = departmentRespository.findOne(departmentId);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(department, departmentDTO);
        return departmentDTO;
    }

    @Override
    public List<Department> findAll() {
        return departmentRespository.findAll();
    }

    @Override
    public List<DepartmentDTO> findAllDTO() {
        List<Department> departments = departmentRespository.findAll();
        List<DepartmentDTO> departmentDTOS = new ArrayList<>();
        BeanUtils.copyProperties(departments, departmentDTOS);
        return departmentDTOS;
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRespository.findAll(pageable);
    }

    @Override
    public Page<DepartmentDTO> findAllDTO(Pageable pageable) {
        Page<Department> departmentPage = departmentRespository.findAll(pageable);
        List<DepartmentDTO> departmentDTOPage = Department2DepartmentDTOConverter.convert(departmentPage.getContent());
        return new PageImpl<>(departmentDTOPage,pageable,departmentPage.getTotalElements());
    }

    @Override
    public List<Department> findAll(Sort sort) {
        return departmentRespository.findAll(sort);
    }

    @Override
    public List<DepartmentDTO> findAllDTO(Sort sort) {
        List<Department> departments = departmentRespository.findAll(sort);
        List<DepartmentDTO> departmentDTOS =Department2DepartmentDTOConverter.convert(departments);
        return departmentDTOS;
    }

    @Override
    public DepartmentDTO saveDTO(Department department) {
        Department save = departmentRespository.save(department);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        BeanUtils.copyProperties(save,departmentDTO);
        return departmentDTO;
    }

    @Override
    public Department save(Department department) {

        return departmentRespository.save(department);
    }

    @Override
    public Department saveAndFlush(Department department) {

        return departmentRespository.saveAndFlush(department);
    }

    @Override
    public List<Department> save(List<Department> departments) {
        return departmentRespository.save(departments);
    }

    @Override
    public void delete(Long departmentId) {
        departmentRespository.delete(departmentId);
    }
}
