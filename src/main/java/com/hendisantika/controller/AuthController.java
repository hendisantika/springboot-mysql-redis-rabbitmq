package com.hendisantika.controller;

import com.hendisantika.repository.UserRepository;
import com.hendisantika.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/04/22
 * Time: 06.56
 */
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "API to manage Auth Controller")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository users;

    // tags = {"auth"}
}
