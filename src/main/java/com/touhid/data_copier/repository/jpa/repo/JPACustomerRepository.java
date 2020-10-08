package com.touhid.data_copier.repository.jpa.repo;

import com.touhid.data_copier.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Touhid Hossain
 */
public interface JPACustomerRepository extends JpaRepository<Customer, Integer> {
}
