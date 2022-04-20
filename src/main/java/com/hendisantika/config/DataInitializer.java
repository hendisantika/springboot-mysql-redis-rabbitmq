package com.hendisantika.config;

import com.hendisantika.entity.User;
import com.hendisantika.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 22.01
 */
@Component
@Slf4j
public class DataInitializer implements CommandLineRunner {
    @Autowired
    UserRepository users;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //...
        this.users.save(User.builder()
                .username("user")
                .name("simple user")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER"))
                .build()
        );
        this.users.save(User.builder()
                .username("admin")
                .name("simple admin")
                .password(this.passwordEncoder.encode("password"))
                .roles(Arrays.asList("ROLE_USER", "ROLE_ADMIN"))
                .build()
        );
        log.debug("printing all users...");
        this.users.findAll().forEach(v -> log.debug(" User :" + v.toString()));
    }
}
