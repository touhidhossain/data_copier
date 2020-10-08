package com.touhid.data_copier.service;

import com.touhid.data_copier.entity.Customer;
import com.touhid.data_copier.repository.jpa.repo.JPACustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Touhid Hossain
 */
@Service
public class JPACustomerService {

    private final JPACustomerRepository jpaCustomerRepository;

    public JPACustomerService(JPACustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return jpaCustomerRepository.save(customer);
    }

    public List<Customer> saveListOfCustomer(List<Customer> customers) {
        return jpaCustomerRepository.saveAll(customers);
    }

    public Optional<Customer> getCustomerById(int customerId) {
        return jpaCustomerRepository.findById(customerId);
    }

    public List<Customer> getAllCustomer() {
        return jpaCustomerRepository.findAll();
    }
}
