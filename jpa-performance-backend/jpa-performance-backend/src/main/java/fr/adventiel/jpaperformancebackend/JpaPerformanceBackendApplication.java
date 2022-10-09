package fr.adventiel.jpaperformancebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JpaPerformanceBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaPerformanceBackendApplication.class, args);
    }

}
