package com.fyerp.admin.service;

import com.fyerp.admin.domain.CameraSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/10
 * @Time:17:29
 **/
public interface CameraSupplierService {
    CameraSupplier findOne(Integer id);

    List<CameraSupplier> findAll();

    Page<CameraSupplier> findAll(Pageable page);

    List<CameraSupplier>  findAll(Sort sort);

    CameraSupplier save(CameraSupplier cameraSupplier);

    void delete(Integer id);
}
