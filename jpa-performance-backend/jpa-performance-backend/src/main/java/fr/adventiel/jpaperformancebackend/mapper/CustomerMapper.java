package fr.adventiel.jpaperformancebackend.mapper;

import fr.adventiel.jpaperformancebackend.dto.CustomerDto;
import fr.adventiel.jpaperformancebackend.entity.Customer;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    @Mapping(target = "commands", ignore = true)
    CustomerDto toDto(Customer customer);

    @Named("customerToDtoWithCommands")
    CustomerDto toDtoWithCommands(Customer customer);

    Customer toEntity(CustomerDto customerDto);

    List<CustomerDto> toDtos(List<Customer> customerList);

    @IterableMapping(qualifiedByName = "customerToDtoWithCommands")
    List<CustomerDto> toDtosWithCommands(List<Customer> customerList);

    List<Customer> toEntities(List<CustomerDto> customerDtos);
}
