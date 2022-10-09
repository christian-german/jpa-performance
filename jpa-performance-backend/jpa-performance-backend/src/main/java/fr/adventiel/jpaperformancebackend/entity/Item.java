package fr.adventiel.jpaperformancebackend.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

/**
 * <p>Item entity.</p>
 * Has a many to one relationship with {@link Command}.
 */
@Entity
@Data
public class Item {

    /**
     * Unique identifier of the {@link Item}.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Code of the item.
     */
    private String code;

    /**
     * Quantity of the item in the command.
     */
    private int quantity;

    /**
     * Price of the item.
     */
    private Double price;

    /**
     * Command which contains the item.
     */
    @ManyToOne
    @ToString.Exclude
    private Command command;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) && Objects.equals(code, item.code) && Objects.equals(quantity, item.quantity) && Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, quantity, price);
    }
}
