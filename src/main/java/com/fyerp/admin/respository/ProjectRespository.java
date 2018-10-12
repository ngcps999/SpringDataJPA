/*
 * Copyright (c) 2018.
 * 项目名称：fyerp.
 * 模块名称：fyerp
 * 文件名称：ProjectRespository.java
 * 作者：xuda
 * 时间：18-4-3 下午3:38
 *
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Project;
import com.fyerp.admin.respository.BaseRespository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * 项目持久层Dao
 */

public interface ProjectRespository extends BaseRepository<Project, Integer>,JpaSpecificationExecutor<Project> {

    List<Project> findProjectsByProjectState(Integer projectState);

    Project findProjectByProjectId(Integer projectId);

    Project findByProjectName(String projectName);

    List<Project> findByPlanStartDateBetween(Date planStartDate1,Date planStartDate2);

    List<Project> findByPlanStartDateAfterAndPlanEndDateBefore(Date planStartDate,Date planEndDate);

    List<Project> findByRealStartDateAfterAndRealEndDateBefore(Date realStartDate,Date realEndDate);

    Page<Project> findByPriority(Integer priority,Pageable pageable);

    Page<Project> findAll(Specification<Project> specification, Pageable pageable);

    @Query(value = "select project_id from project_department where department_id in (:ids)", nativeQuery = true)
    List<Integer> findProjectIdsByDepartmentIdList(@Param("ids") List<Long> ids);
}
