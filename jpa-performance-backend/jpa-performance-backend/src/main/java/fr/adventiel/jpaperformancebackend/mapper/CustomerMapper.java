package fr.adventiel.jpaperformancebackend.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import fr.adventiel.jpaperformancebackend.dto.CustomerDto;
import fr.adventiel.jpaperformancebackend.entity.Customer;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "commands", ignore = true)
    CustomerDto toDto(Customer customer);

    Customer toEntity(CustomerDto customerDto);

    List<CustomerDto> toDtos(List<Customer> customerList);

    List<Customer> toEntities(List<CustomerDto> customerDtos);
}
