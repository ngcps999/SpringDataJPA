package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.AircraftLeaseSupplier;
import com.fyerp.admin.respository.AircraftLeaseSupplierRepository;
import com.fyerp.admin.service.AircraftLeaseSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/12
 * @Time:9:47
 **/
@Service
public class AircraftLeaseSupplierServiceImpl implements AircraftLeaseSupplierService {

    @Autowired
    private AircraftLeaseSupplierRepository repository;

    @Override
    public AircraftLeaseSupplier findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Page<AircraftLeaseSupplier> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<AircraftLeaseSupplier> findAll() {
        return repository.findAll();
    }

    @Override
    public List<AircraftLeaseSupplier> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public AircraftLeaseSupplier save(AircraftLeaseSupplier supplier) {
        return repository.save(supplier);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
