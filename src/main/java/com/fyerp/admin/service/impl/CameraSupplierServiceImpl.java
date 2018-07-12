package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.CameraSupplier;
import com.fyerp.admin.respository.CameraSupplierRepository;
import com.fyerp.admin.service.CameraSupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/12
 * @Time:9:49
 **/
@Service
public class CameraSupplierServiceImpl implements CameraSupplierService {

    @Autowired
    private CameraSupplierRepository repository;


    @Override
    public CameraSupplier findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<CameraSupplier> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<CameraSupplier> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<CameraSupplier> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public CameraSupplier save(CameraSupplier cameraSupplier) {
        return repository.save(cameraSupplier);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
