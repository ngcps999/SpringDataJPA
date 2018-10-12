package com.fyerp.admin.service.impl;

import com.fyerp.admin.domain.Customer;
import com.fyerp.admin.respository.CustomerRepository;
import com.fyerp.admin.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:xiasc
 * @Date:2018/7/12
 * @Time:9:56
 **/
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    @Override
    public Customer findOne(Integer customerId) {
        return repository.findOne(customerId);
    }

    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Customer> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    @Override
    public Customer save(Customer customer) {
        return repository.save(customer);
    }

    @Override
    public void delete(Integer customerId) {
        repository.delete(customerId);
    }
}
