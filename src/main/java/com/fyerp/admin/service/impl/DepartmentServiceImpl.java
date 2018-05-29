/*
 * 作者：xuda
 * 创建时间：18-5-8 上午11:12
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Department;
import com.fyerp.admin.domain.User;
import com.fyerp.admin.domain.dto.DepartmentDTO;
import com.fyerp.admin.domain.dto.UserDTO;
import com.fyerp.admin.respository.DepartmentRespository;
import com.fyerp.admin.respository.UserRespository;
import com.fyerp.admin.service.DepartmentService;
import com.fyerp.admin.utils.BeanUtils;
import com.fyerp.admin.utils.convert.Department2DepartmentDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.fyerp.admin.utils.BeanUtils.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Autowired
    private DepartmentRespository departmentRespository;

    @Autowired
    private UserRespository userRespository;

    @Override
    public Department findOne(Long departmentId) {
        return departmentRespository.findOne(departmentId);
    }

    @Override
    public DepartmentDTO findOneDTO(Long departmentId) {

        Department department = departmentRespository.findOne(departmentId);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        copyNotNullProperties(department, departmentDTO);
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
        copyNotNullProperties(departments, departmentDTOS);
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
    public Department save(Department department) {

        return departmentRespository.save(department);
    }
    @Override
    public DepartmentDTO saveDTO(Department department) {

        Department department1 = departmentRespository.saveAndFlush(department);
        DepartmentDTO departmentDTO = Department2DepartmentDTOConverter.convert(department1);
        return departmentDTO;
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

    @Override
    public Integer deleteA(Long departmentId,Long userId) {
        return departmentRespository.deleteA(userId);
    }

    @Override
    public Integer update(Long departmentId, Long userId) {
        return departmentRespository.update(departmentId,userId);
    }

    @Override
    public Integer insert(Long departmentId, Long userId) {
        return departmentRespository.insert(departmentId,userId);
    }
}
