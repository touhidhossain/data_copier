package com.touhid.data_copier.service;

import com.touhid.data_copier.entity.Customer;
import com.touhid.data_copier.repository.mongo.repo.MongoCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Touhid Hossain
 */
@Service
public class MongoCustomerService {

    private final MongoCustomerRepository mongoCustomerRepository;

    public MongoCustomerService(MongoCustomerRepository mongoCustomerRepository){
        this.mongoCustomerRepository = mongoCustomerRepository;
    }

    public Customer saveCustomer(Customer customer) {
        return mongoCustomerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(int customerId) {
        return mongoCustomerRepository.findById(customerId);
    }

    public List<Customer> getAllCustomer() {
        return mongoCustomerRepository.findAll();
    }

    public List<Customer> saveListOfCustomer(List<Customer> customers) {
        return mongoCustomerRepository.saveAll(customers);
    }
}
