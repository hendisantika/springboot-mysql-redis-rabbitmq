package com.hendisantika.service;

import com.hendisantika.entity.User;
import com.hendisantika.event.UserCreationEvent;
import com.hendisantika.exception.ResourceNotFoundException;
import com.hendisantika.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 22.04
 */
@Slf4j
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RedisTemplate<String, User> redisTemplate;
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public User findUserById(Long id) {
        final String key = "user_" + id;
        final ValueOperations<String, User> operations = redisTemplate.opsForValue();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            final User user = operations.get(key);
            log.info("UserServiceImpl.findUserById() : cache user >> " + user.toString());
            return user;
        }
        final Optional<User> user = Optional.ofNullable(userRepository.findById(id).orElse(null));
        if (user.isPresent()) {
            operations.set(key, user.get(), 10, TimeUnit.SECONDS);
            log.info("UserServiceImpl.findUserById() : cache insert >> " + user.get());
            return user.get();
        } else {
            throw new ResourceNotFoundException();
        }
    }

    public Page<User> getAllUsers(Integer page, Integer size) {
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public User saveUser(User user) {
        User savedUser = userRepository.save(user);
        applicationEventPublisher.publishEvent(new UserCreationEvent<User>(savedUser));
        return savedUser;
    }

    @Override
    public User updateUser(User user) {
        final String key = "user_" + user.getId();
        final boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
            log.info("UserServiceImpl.updateUser() : cache update >> " + user);
        }
        return userRepository.save(user);
    }
}
