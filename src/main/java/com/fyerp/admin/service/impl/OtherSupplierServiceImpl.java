package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.OtherSupplier;
import com.fyerp.admin.domain.Supplier;
import com.fyerp.admin.respository.OtherSupplierRepository;
import com.fyerp.admin.service.OtherSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/12
 * @Time:9:51
 **/
@Service(value = "otherSupplierService")
public class OtherSupplierServiceImpl implements OtherSupplierService {

    @Autowired
    private OtherSupplierRepository repository;

    @Override
    public Supplier findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Page findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<OtherSupplier> findAll() {
        return repository.findAll();
    }

    @Override
    public List<OtherSupplier> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Supplier save(Supplier supplier) {
        return repository.save((OtherSupplier) supplier);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
