package com.touhid.data_copier.repository.mongo.repo;

import com.touhid.data_copier.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Touhid Hossain
 */
public interface MongoCustomerRepository extends MongoRepository<Customer, Integer> {
}
