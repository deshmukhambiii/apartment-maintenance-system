package com.apartment.maintenance.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationTest {

    @Test
    void toStringContainsMessageAndTimestampBrackets() {
        Notification notification = new Notification("Complaint registered");

        String text = notification.toString();

        assertTrue(text.startsWith("["));
        assertTrue(text.contains("] Complaint registered"));
    }
}
