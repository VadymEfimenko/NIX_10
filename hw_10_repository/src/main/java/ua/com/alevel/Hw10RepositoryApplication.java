package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class Hw10RepositoryApplication {

    public static void main(String[] args) {
        SpringApplication.run(Hw10RepositoryApplication.class, args);
    }

}
