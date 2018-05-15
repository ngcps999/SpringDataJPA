/*
 * 作者：xuda
 * 创建时间：18-5-14 上午9:04
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectCateroryRespository extends JpaRepository<ProjectCategory,Integer> {
    ProjectCategory findByCategoryType(Integer categoryType);
    List<ProjectCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
}
