/*
 * 作者：xuda
 * 创建时间：18-5-14 上午9:05
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.ProjectCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProjectCategoryService {

    /**
     * 查询单个角色
     *
     * @param categoryId
     * @return
     */
    ProjectCategory findOne(Integer categoryId);

    /**
     * 查询所有角色
     * @return
     */
    List<ProjectCategory> findAll();

    Page<ProjectCategory> findAll(Pageable pageable);

    List<ProjectCategory> findAll(Sort sort);

    /**
     * 新增/更新角色
     * @param projectCatogory
     * @return
     */
    ProjectCategory save(ProjectCategory projectCatogory);

    /**
     * 删除角色
     *
     * @param categoryId
     */
    void delete(Integer categoryId);
}
