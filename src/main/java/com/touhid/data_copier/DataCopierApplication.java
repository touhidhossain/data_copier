package com.touhid.data_copier;

import com.touhid.data_copier.entity.Customer;
import com.touhid.data_copier.service.MongoCustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.touhid.data_copier.repository.jpa.repo")
@EnableMongoRepositories(basePackages = "com.touhid.data_copier.repository.mongo.repo")
@EntityScan(basePackages = "com.touhid.data_copier.entity")
public class DataCopierApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataCopierApplication.class, args);
    }

    @Bean
    CommandLineRunner saveInitialData(MongoCustomerService mongoCustomerService) {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1, "Touhid", "Hossain", "hktouhid@gmail.com", "+8801674706343", true));
        customers.add(new Customer(2, "MD", "Nayem", "nayem@gmail.com", "+8801674X6343", false));
        customers.add(new Customer(3, "Asad", "Uzzaman", "asad@gmail.com", "+880167X706343", true));
        customers.add(new Customer(4, "Sajib", "Baroi", "sajib@gmail.com", "+8801674X06343", false));
        customers.add(new Customer(5, "Imran", "Hasan", "imran@gmail.com", "+88016747X6343", true));
        return args -> mongoCustomerService.saveListOfCustomer(new ArrayList<>(customers));
    }
}
