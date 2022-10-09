package fr.adventiel.jpaperformancebackend.repository;

import fr.adventiel.jpaperformancebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
