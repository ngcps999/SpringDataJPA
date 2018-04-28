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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

/**
 * 项目持久层Dao
 */

public interface ProjectRespository extends JpaRepository<Project, Integer> {

    List<Project> findProjectsByProjectState(Integer projectState);

    Project findProjectByProjectId(Integer projectId);

    Project findByProjectName(String projectName);

    List<Project> findByPlanStartDateBetween(String planStartDate1,String planStartDate2);

}
