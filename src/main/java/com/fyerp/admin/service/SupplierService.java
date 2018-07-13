package com.fyerp.admin.service;

import com.fyerp.admin.domain.OtherSupplier;
import com.fyerp.admin.domain.Supplier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/13
 * @Time:14:03
 **/
public interface SupplierService {
    Supplier findOne(Integer id);

    Page findAll(Pageable page);

    List findAll();

    List findAll(Sort sort);

    Supplier save(Supplier supplier);

    void delete(Integer id);
}
