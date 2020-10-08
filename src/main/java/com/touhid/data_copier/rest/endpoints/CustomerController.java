package com.touhid.data_copier.rest.endpoints;

import com.touhid.data_copier.entity.Customer;
import com.touhid.data_copier.service.JPACustomerService;
import com.touhid.data_copier.service.MongoCustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @author Touhid Hossain
 */
@RestController
public class CustomerController {

    private final MongoCustomerService mongoCustomerService;
    private final JPACustomerService jpaCustomerService;

    CustomerController(MongoCustomerService mongoCustomerService, JPACustomerService jpaCustomerService) {
        this.mongoCustomerService = mongoCustomerService;
        this.jpaCustomerService = jpaCustomerService;
    }

    /**
     * Insert records from MongoDB to MySql
     */

    @GetMapping(value = "/mongo_to_mysql/customer/{id}")
    public ResponseEntity<Customer> getAllCustomerFromMySql(@PathVariable(value = "id") int customerId) {
        return mongoCustomerService.getCustomerById(customerId)
                .map(customer -> ResponseEntity.ok(jpaCustomerService.saveCustomer(customer)))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "No Customer exist with id " + customerId));
    }

    @GetMapping(value = "/mongo_to_mysql/customers")
    public ResponseEntity<List<Customer>> copyAllCustomersFromMongoDBtoMySql() {
        List<Customer> customers = mongoCustomerService.getAllCustomer();
        return customers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(jpaCustomerService.saveListOfCustomer(customers));
    }


    /**
     * Rest End points for /mongo mapping
     */

    @PostMapping(value = "/mongo/customer")
    public ResponseEntity<Customer> saveCustomerToMongoDB(@RequestBody Customer customer) {
        Optional<Customer> optionalCustomer = mongoCustomerService.getCustomerById(customer.getId());
        if (optionalCustomer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exist with id " + customer.getId());
        }
        return ResponseEntity.ok(mongoCustomerService.saveCustomer(customer));
    }

    @GetMapping(value = "/mongo/customer/{id}")
    public ResponseEntity<Customer> getCustomerFromMongoDB(@PathVariable("id") int id) {
        return mongoCustomerService.getCustomerById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/mongo/customers")
    public ResponseEntity<List<Customer>> getAllCustomerFromMongoDB() {
        return ResponseEntity.ok(mongoCustomerService.getAllCustomer());
    }

    /**
     * Rest End points for /mysql mapping
     */

    @PostMapping(value = "/mysql/customer")
    public ResponseEntity<Customer> saveCustomerToMySql(@RequestBody Customer customer) {
        Optional<Customer> optionalCustomer = jpaCustomerService.getCustomerById(customer.getId());
        if (optionalCustomer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Customer already exist with id " + customer.getId());
        }
        return ResponseEntity.ok(jpaCustomerService.saveCustomer(customer));
    }

    @GetMapping(value = "/mysql/customer/{id}")
    public ResponseEntity<Customer> getCustomerFromMySql(@PathVariable("id") int customerId) {
        return jpaCustomerService.getCustomerById(customerId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping(value = "/mysql/customers")
    public ResponseEntity<List<Customer>> getAllCustomerFromMySql() {
        return ResponseEntity.ok(jpaCustomerService.getAllCustomer());
    }
}
