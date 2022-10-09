package fr.adventiel.jpaperformancebackend.controller;

import fr.adventiel.jpaperformancebackend.dto.CustomerDto;
import fr.adventiel.jpaperformancebackend.entity.Customer;
import fr.adventiel.jpaperformancebackend.mapper.CustomerMapper;
import fr.adventiel.jpaperformancebackend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>Access to the {@link Customer} resource.</p>
 * Accepts requests at /customers and takes care of mapping the {@link CustomerDto} dto to the {@link Customer} entity and the other way around.
 */
@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("{customerId}")
    public CustomerDto findById(@PathVariable Long customerId) {
        return customerMapper.toDto(customerService.findById(customerId));
    }

    @GetMapping
    public List<CustomerDto> findAll(@RequestParam(defaultValue = "false") boolean loadCommands) {
        if (loadCommands) {
            return customerMapper.toDtosWithCommands(customerService.findAllWithCommands());
        }
        return customerMapper.toDtos(customerService.findAll());
    }
}
