package com.fyerp.admin.service;


import com.fyerp.admin.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/10
 * @Time:17:26
 **/
public interface CustomerService {
    Customer findOne(Integer customerId);

    Page<Customer> findAll(Pageable pageable);

    List<Customer> findAll();

    List<Customer> findAll(Sort sort);

    Customer save(Customer customer);

    void delete(Integer customerId);
}
