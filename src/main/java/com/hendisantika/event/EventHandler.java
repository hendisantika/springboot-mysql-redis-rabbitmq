package com.hendisantika.event;

import com.hendisantika.config.WebSocketConfig;
import com.hendisantika.entity.User;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.event.EventListener;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-mysql-redis-rabbitmq
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/04/22
 * Time: 21.50
 */
@Component
public class EventHandler {
    private final Log log = LogFactory.getLog(getClass());
    private final SimpMessagingTemplate simpMessagingTemplate;

    private final EntityLinks entityLinks;

    public EventHandler(SimpMessagingTemplate simpMessagingTemplate, EntityLinks entityLinks) {
        this.simpMessagingTemplate = simpMessagingTemplate;
        this.entityLinks = entityLinks;
    }

    @EventListener
    public void newUser(UserCreationEvent<User> savedUser) {
        log.info("New User created Event");

        this.simpMessagingTemplate.convertAndSend(
                WebSocketConfig.MESSAGE_PREFIX + "/newUser", getPath(savedUser.getUser()));
    }

    @EventListener
    public void deleteUser(UserDeletionEvent<User> deletedUser) {
        log.info("User deleted Event");
        this.simpMessagingTemplate.convertAndSend(
                WebSocketConfig.MESSAGE_PREFIX + "/deleteUser", getPath(deletedUser.getUser()));
    }
}
