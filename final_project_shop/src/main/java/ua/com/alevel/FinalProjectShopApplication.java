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
import ua.com.alevel.persistence.repository.AdminRepository;
import ua.com.alevel.persistence.repository.MusicianRepository;

import java.util.Date;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
public class FinalProjectShopApplication {

    private final BCryptPasswordEncoder encoder;
    private final AdminRepository adminRepository;
    private final MusicianRepository musicianRepository;

    public FinalProjectShopApplication(BCryptPasswordEncoder encoder, AdminRepository adminRepository, MusicianRepository musicianRepository) {
        this.encoder = encoder;
        this.adminRepository = adminRepository;
        this.musicianRepository = musicianRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinalProjectShopApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void listen() {
        Admin admin = adminRepository.findByEmail("admin@mail.com");
        if (admin == null) {
            admin = new Admin();
            admin.setEmail("admin@mail.com");
            admin.setCreated(new Date(System.currentTimeMillis()));
            admin.setUpdated(new Date(System.currentTimeMillis()));
            admin.setPassword(encoder.encode("rootroot"));
            adminRepository.save(admin);
        }
    }
}
