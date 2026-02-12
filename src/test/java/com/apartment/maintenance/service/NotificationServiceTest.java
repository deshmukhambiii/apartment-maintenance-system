package com.apartment.maintenance.service;

import com.apartment.maintenance.domain.model.Resident;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class NotificationServiceTest {

    @Test
    void notifyResidentPrintsExpectedText() {
        NotificationService service = new NotificationService();
        Resident resident = new Resident("Amit Sharma", "amit@gmail.com");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            service.notifyResident(resident, "Test message");
        } finally {
            System.setOut(originalOut);
        }

        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Notification to amit@gmail.com:"));
        assertTrue(output.contains("Test message"));
    }
}
