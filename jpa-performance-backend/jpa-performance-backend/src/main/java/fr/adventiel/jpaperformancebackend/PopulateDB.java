package fr.adventiel.jpaperformancebackend;

import com.github.javafaker.Faker;
import fr.adventiel.jpaperformancebackend.entity.Command;
import fr.adventiel.jpaperformancebackend.entity.Customer;
import fr.adventiel.jpaperformancebackend.entity.Item;
import fr.adventiel.jpaperformancebackend.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * <p>Loads the database with fake data.</p>
 * Only runs if the property {@code load} is set to true.
 * The number of customers to create is set by the property {@code load-count}.
 * The number of associated commands and items is set randomly.
 *
 * @see CommandLineRunner
 */
@Component
@ConditionalOnProperty(value = "load", havingValue = "true")
@Slf4j
public class PopulateDB implements CommandLineRunner {

    @Value("${load-count:10}")
    private Integer loadCount;

    private final CustomerRepository customerRepository;
    private final Faker faker;

    public PopulateDB(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        faker = new Faker(new Locale("fr-FR"));
    }

    @Override
    public void run(String... args) {
        customerRepository.deleteAll();

        log.info("Creating {} customers", loadCount);

        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < loadCount; i++) {
            customers.add(createCustomerEntityWithCommands());
        }

        Integer commandsNumber = customers.stream().map(customer -> customer.getCommands().size()).reduce(0, Integer::sum);

        log.info("Inserting {} customers in DB with {} commands", loadCount, commandsNumber);

        customerRepository.saveAll(customers);

        log.info("done");
    }

    private Customer createCustomerEntityWithCommands() {
        Customer customer = new Customer();
        customer.setEmail(faker.internet().emailAddress());
        customer.setFirstname(faker.name().firstName());
        customer.setLastname(faker.name().lastName());
        customer.setNumber(faker.number().numberBetween(1, 99999));

        int commandsNumber = faker.number().numberBetween(1, 10);
        for (int i = 0; i < commandsNumber; i++) {
            Command command = createCommandEntityWithItems();
            customer.addCommand(command);
        }

        return customer;
    }

    private Command createCommandEntityWithItems() {
        Command command = new Command();
        command.setNumber(faker.number().numberBetween(1, 99999));

        LocalDate localDate = faker.date()
                .birthday()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        command.setOrderedAt(localDate);

        int commandsNumber = faker.number().numberBetween(1, 10);
        for (int i = 0; i < commandsNumber; i++) {
            Item item = createItemEntity();
            command.addItem(item);
        }

        return command;
    }

    private Item createItemEntity() {
        Item item = new Item();
        item.setCode(faker.random().hex());
        item.setPrice(faker.random().nextDouble());
        item.setQuantity(faker.random().nextInt(2));

        return item;
    }
}
