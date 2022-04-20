package com.hendisantika.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 21.56
 */
@Component
public class JwtTokenProvider {
    @Autowired
    JwtProperties jwtProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    private String secretKey;
}
