package com.apartment.maintenance.domain.model;
import java.time.LocalDateTime;

public class Notification {
    private final String message;
    private final LocalDateTime timestamp;

    public Notification(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + timestamp + "] " + message;
    }
}

