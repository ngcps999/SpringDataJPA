/*
 * 作者：xuda
 * 创建时间：18-4-20 下午4:32
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRespository extends JpaRepository<Plan,Integer> {
}
