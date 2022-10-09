package fr.adventiel.jpaperformancebackend.repository;

import fr.adventiel.jpaperformancebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query("SELECT distinct c FROM Customer c LEFT JOIN FETCH c.commands commands LEFT JOIN FETCH commands.items items")
    List<Customer> findAllWithCommands();

}
