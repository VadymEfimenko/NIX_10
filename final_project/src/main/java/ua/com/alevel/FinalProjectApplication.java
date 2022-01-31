package ua.com.alevel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ua.com.alevel.persistence.entity.user.Admin;
import ua.com.alevel.persistence.repository.user.AdminRepository;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class FinalProjectApplication {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;

    public FinalProjectApplication(BCryptPasswordEncoder encoder, AdminRepository adminRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        Admin admin = adminRepository.findByEmail("admin@mail.com");
        if (admin == null) {
            admin = new Admin();
            admin.setEmail("admin@mail.com");
            admin.setPassword(encoder.encode("rootroot"));
            adminRepository.save(admin);
        }
    }
}
