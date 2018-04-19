/*
 * 作者：xuda
 * 创建时间：18-4-18 下午3:50
 * 模块名称：admin
 */

package com.fyerp.admin.service;

import com.fyerp.admin.domain.Contract;

import java.util.List;

/**
 * 合同服务层
 */
public interface ContractService {

    public Contract findOne(Integer contractId);

    public List<Contract> findAll();

    public Contract save(Contract contract);

    public void delete(Integer contractId);
}
