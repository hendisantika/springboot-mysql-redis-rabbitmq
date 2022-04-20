package com.hendisantika.event;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 21.52
 */
public class UserCreationEvent<User> extends EntityEvent<User> {

    private final User user;

    public UserCreationEvent(User user) {
        super(user);
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
