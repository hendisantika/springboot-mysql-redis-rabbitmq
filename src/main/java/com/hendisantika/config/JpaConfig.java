package com.hendisantika.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 21.37
 */
@Configuration
@EnableJpaRepositories("com.hendisantika.repository")
@EntityScan(basePackageClasses = {Jsr310JpaConverters.class}, basePackages = {"com.hendisantika.entity"})
public class JpaConfig {
}
