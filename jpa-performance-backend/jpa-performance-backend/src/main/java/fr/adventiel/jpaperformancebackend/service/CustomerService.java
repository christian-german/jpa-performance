package fr.adventiel.jpaperformancebackend.service;

import java.util.List;

import fr.adventiel.jpaperformancebackend.entity.Customer;

public interface CustomerService {

    Customer findById(Long id);

    List<Customer> findAll();

}
