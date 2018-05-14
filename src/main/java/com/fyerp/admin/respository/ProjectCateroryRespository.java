/*
 * 作者：xuda
 * 创建时间：18-5-14 上午9:04
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.ProjectCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectCateroryRespository extends JpaRepository<ProjectCategory,Integer> {
}
