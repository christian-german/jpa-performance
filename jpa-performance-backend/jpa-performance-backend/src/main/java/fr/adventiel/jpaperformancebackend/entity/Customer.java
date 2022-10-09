package fr.adventiel.jpaperformancebackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Customer entity.</p>
 * Has a one to many relationship with {@link Command}. Commands are fetch eagerly.
 */
@Entity
@Data
public class Customer {

    /**
     * Unique identifier of the {@link Customer}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Number of the customer.
     */
    private int number;

    /**
     * Firstname of the customer.
     */
    private String firstname;

    /**
     * Lastname of the customer.
     */
    private String lastname;

    /**
     * Email of the customer.
     */
    private String email;

    /**
     * List of commands of the customer.
     */
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    Set<Command> commands = new HashSet<>();

    /**
     * <p>Adds a {@link Command} to the {@link Customer}.</p>
     * Sets the {@link Customer} as the parent of the {@link Command}.
     *
     * @param command the {@link Command} to add
     */
    public void addCommand(Command command) {
        commands.add(command);
        command.setCustomer(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) && Objects.equals(number, customer.number) && Objects.equals(firstname, customer.firstname) && Objects.equals(lastname, customer.lastname) && Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, firstname, lastname, email);
    }
}
