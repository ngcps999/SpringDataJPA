/*
 * 作者：xuda
 * 创建时间：18-4-18 下午3:51
 * 模块名称：admin
 */

package com.fyerp.admin.respository;

import com.fyerp.admin.domain.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ContractRespository extends JpaRepository<Contract,Integer> {

}
