/*
 * 作者：xuda
 * 创建时间：18-5-14 上午9:09
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.ProjectCategory;
import com.fyerp.admin.respository.ProjectCateroryRespository;
import com.fyerp.admin.service.ProjectCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectCategoryServiceImpl implements ProjectCategoryService {

    @Autowired
    private ProjectCateroryRespository cateroryRespository;

    @Override
    public ProjectCategory findOne(Integer categoryId) {
        return cateroryRespository.findOne(categoryId);
    }

    @Override
    public ProjectCategory findByCategoryType(Integer categoryType) {
        return cateroryRespository.findByCategoryType(categoryType);
    }

    @Override
    public List<ProjectCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return cateroryRespository.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public List<ProjectCategory> findAll() {
        return cateroryRespository.findAll();
    }

    @Override
    public Page<ProjectCategory> findAll(Pageable pageable) {
        return cateroryRespository.findAll(pageable);
    }

    @Override
    public List<ProjectCategory> findAll(Sort sort) {
        return cateroryRespository.findAll(sort);
    }

    @Override
    public ProjectCategory save(ProjectCategory projectCatogory) {
        return cateroryRespository.save(projectCatogory);
    }

    @Override
    public void delete(Integer categoryId) {
        cateroryRespository.delete(categoryId);
    }
}
