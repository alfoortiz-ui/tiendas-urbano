package com.tiendasurbano.notification;

public interface NotificationSender {
    void send(String recipient, String subject, String body);
}
