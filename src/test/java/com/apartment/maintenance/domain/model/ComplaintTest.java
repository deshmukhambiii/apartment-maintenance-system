package com.apartment.maintenance.domain.model;

import com.apartment.maintenance.domain.enums.ComplaintCategory;
import com.apartment.maintenance.domain.enums.ComplaintStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComplaintTest {

    @Test
    void constructorInitializesComplaintFields() {
        Resident resident = new Resident("Amit Sharma", "amit@gmail.com");
        Complaint complaint = new Complaint(resident, ComplaintCategory.PLUMBING, "Leakage");

        assertNotNull(complaint.getComplaintId());
        assertEquals(resident, complaint.getResident());
        assertEquals(ComplaintCategory.PLUMBING, complaint.getCategory());
        assertEquals("Leakage", complaint.getDescription());
        assertEquals(ComplaintStatus.OPEN, complaint.getStatus());
        assertNotNull(complaint.getCreatedAt());
        assertEquals(complaint.getCreatedAt(), complaint.getUpdatedAt());
    }

    @Test
    void updateStatusChangesComplaintStatusAndUpdatedAt() throws InterruptedException {
        Resident resident = new Resident("Amit Sharma", "amit@gmail.com");
        Complaint complaint = new Complaint(resident, ComplaintCategory.SECURITY, "Lock broken");
        var createdTime = complaint.getUpdatedAt();

        Thread.sleep(5);
        complaint.updateStatus(ComplaintStatus.IN_PROGRESS);

        assertEquals(ComplaintStatus.IN_PROGRESS, complaint.getStatus());
        assertTrue(complaint.getUpdatedAt().isAfter(createdTime));
    }

    @Test
    void toStringIncludesImportantFields() {
        Resident resident = new Resident("Amit Sharma", "amit@gmail.com");
        Complaint complaint = new Complaint(resident, ComplaintCategory.CLEANING, "Lobby cleaning required");

        String text = complaint.toString();

        assertTrue(text.contains("Complaint{"));
        assertTrue(text.contains("category=CLEANING"));
        assertTrue(text.contains("status=OPEN"));
        assertTrue(text.contains("description='Lobby cleaning required'"));
    }
}
