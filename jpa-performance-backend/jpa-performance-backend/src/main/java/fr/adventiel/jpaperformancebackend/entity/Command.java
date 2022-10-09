package fr.adventiel.jpaperformancebackend.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * <p>Command entity.</p>
 * Has a many to one relationship with {@link Customer}.
 * Has a one to many relationship with {@link Item}. Items are fetch eagerly.
 */
@Entity
@Data
public class Command {

    /**
     * Unique identifier of the {@link Command}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Number of the command.
     */
    private int number;

    /**
     * Date of the command.
     */
    private LocalDate orderedAt;

    /**
     * Customer who ordered the command.
     */
    @ManyToOne
    @ToString.Exclude
    private Customer customer;

    /**
     * List of items of the command.
     */
    @OneToMany(mappedBy = "command", cascade = CascadeType.ALL)
    Set<Item> items = new HashSet<>();

    /**
     * <p>Adds an {@link Item} to the {@link Command}.</p>
     * Sets the {@link Command} as the parent of the {@link Item}.
     *
     * @param item the {@link Item} to add
     */
    public void addItem(Item item) {
        items.add(item);
        item.setCommand(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Command command = (Command) o;
        return Objects.equals(id, command.id) && Objects.equals(number, command.number) && Objects.equals(orderedAt, command.orderedAt) && Objects.equals(customer, command.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, orderedAt, customer);
    }
}
