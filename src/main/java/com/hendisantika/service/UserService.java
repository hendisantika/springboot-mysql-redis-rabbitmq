package com.hendisantika.service;

import com.hendisantika.entity.User;
import org.springframework.data.domain.Page;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 22.02
 */
public interface UserService {
    User findUserById(Long id);

    Page<User> getAllUsers(Integer page, Integer size);

    User saveUser(User User);

    User updateUser(User User);

    void deleteUser(Long id);
}
