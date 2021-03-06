/*
 * 作者：xuda
 * 创建时间：18-4-18 下午4:24
 * 模块名称：admin
 */

package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Contract;
import com.fyerp.admin.domain.FileInfo;
import com.fyerp.admin.respository.ContractRespository;
import com.fyerp.admin.respository.FileInfoRepository;
import com.fyerp.admin.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;
import java.util.List;
import java.util.Set;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRespository contractRespository;

    @Override
    public Contract findOne(Integer contractId) {
        return contractRespository.findOne(contractId);
    }

    @Override
    public Page<Contract> findAll(Pageable pageable) {
        return contractRespository.findAll(pageable);
    }

    @Override
    public List<Contract> findAll() {
        return contractRespository.findAll();
    }

    @Override
    public List<Contract> findAll(Sort sort) {
        return contractRespository.findAll(sort);
    }

    @Override
    public Contract save(Contract contract) {
        return contractRespository.save(contract);
    }


    @Override
    public void delete(Integer contractId) {
        Contract contract = contractRespository.findOne(contractId);
        Set<FileInfo> files = contract.getFiles();
        files.removeAll(files);

        contractRespository.delete(contractId);

    }
}
