package com.fyerp.admin.service;

import com.fyerp.admin.domain.AircraftLeaseSupplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/10
 * @Time:17:35
 **/
public interface AircraftLeaseSupplierService {

    AircraftLeaseSupplier findOne(Integer id);

    Page<AircraftLeaseSupplier>  findAll(Pageable page);

    List<AircraftLeaseSupplier>  findAll();

    List<AircraftLeaseSupplier>  findAll(Sort sort);

    AircraftLeaseSupplier save(AircraftLeaseSupplier supplier);

    void delete(Integer id);

}
