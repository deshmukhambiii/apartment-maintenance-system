package com.apartment.maintenance.app;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ApartmentMaintenanceAppTest {

    @Test
    void mainRunsAndPrintsComplaintsSummary() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            ApartmentMaintenanceApp.main(new String[0]);
        } finally {
            System.setOut(originalOut);
        }

        String output = out.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("All Complaints:"));
        assertTrue(output.contains("Complaint{"));
        assertTrue(output.contains("updated to IN_PROGRESS"));
        assertTrue(output.contains("updated to RESOLVED"));
    }
}
