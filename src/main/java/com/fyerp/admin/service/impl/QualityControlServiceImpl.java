package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.QualityControl;
import com.fyerp.admin.respository.QualityControlRepository;
import com.fyerp.admin.service.QualityControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/12
 * @Time:9:43
 **/
@Service
public class QualityControlServiceImpl implements QualityControlService {

    @Autowired
    private QualityControlRepository repository;

    @Override
    public QualityControl findOne(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public Page<QualityControl> findAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public List<QualityControl> findAll() {
        return repository.findAll();
    }

    @Override
    public List<QualityControl> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public QualityControl save(QualityControl qualityControl) {
        return repository.save(qualityControl);
    }

    @Override
    public void delete(Integer id) {
        repository.delete(id);
    }
}
