package com.hendisantika.event;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
}
