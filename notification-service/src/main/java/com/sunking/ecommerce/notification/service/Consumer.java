package com.sunking.ecommerce.notification.service;

import com.sunking.ecommerce.notification.model.Notification;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @RabbitListener(queues = "notificationQueue")
    public void receiveNotification(Notification notification) {
        // #TODO
    }
}
