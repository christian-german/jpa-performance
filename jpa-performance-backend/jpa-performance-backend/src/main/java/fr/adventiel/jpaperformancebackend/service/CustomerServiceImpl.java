package fr.adventiel.jpaperformancebackend.service;

import fr.adventiel.jpaperformancebackend.entity.Customer;
import fr.adventiel.jpaperformancebackend.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Find a {link Customer} with the given id.
     *
     * @param id Id of the customer.
     * @return A {@link Customer} entity.
     */
    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow();
    }

    /**
     * Find all customers.
     *
     * @return A list of {@link Customer} entities.
     */
    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    /**
     * Find all customers with their commands.
     *
     * @return A list of {@link Customer} entities.
     */
    public List<Customer> findAllWithCommands() {
        return customerRepository.findAllWithCommands();
    }

}
