package com.hendisantika.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 21.53
 */
public class InvalidJwtAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = -761503632186596342L;

    public InvalidJwtAuthenticationException(String e) {
        super(e);
    }
}
