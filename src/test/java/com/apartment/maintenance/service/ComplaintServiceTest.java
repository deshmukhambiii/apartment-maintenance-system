package com.apartment.maintenance.service;

import com.apartment.maintenance.domain.enums.ComplaintCategory;
import com.apartment.maintenance.domain.enums.ComplaintStatus;
import com.apartment.maintenance.domain.model.Complaint;
import com.apartment.maintenance.domain.model.Resident;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ComplaintServiceTest {

    private static class RecordingNotificationService extends NotificationService {
        private final List<String> messages = new ArrayList<>();

        @Override
        public void notifyResident(Resident resident, String message) {
            messages.add(resident.getEmail() + "|" + message);
        }
    }

    @Test
    void raiseComplaintAutoRegistersResidentAndStoresComplaint() {
        RecordingNotificationService notificationService = new RecordingNotificationService();
        ComplaintService service = new ComplaintService(notificationService);
        Resident resident = new Resident("Amit Sharma", "amit@gmail.com");

        Complaint complaint = service.raiseComplaint(
                resident,
                ComplaintCategory.PLUMBING,
                "Water leakage in bathroom"
        );

        assertNotNull(complaint.getComplaintId());
        assertEquals(complaint, service.getComplaintById(complaint.getComplaintId()));
        assertEquals(1, service.getAllComplaints().size());
        assertEquals(1, service.getComplaintsForResident(resident.getResidentId()).size());
        assertEquals(1, notificationService.messages.size());
        assertTrue(notificationService.messages.get(0).contains("Complaint registered with ID:"));
    }

    @Test
    void updateComplaintStatusUpdatesAndNotifies() {
        RecordingNotificationService notificationService = new RecordingNotificationService();
        ComplaintService service = new ComplaintService(notificationService);
        Resident resident = new Resident("Priya", "priya@gmail.com");

        Complaint complaint = service.raiseComplaint(resident, ComplaintCategory.SECURITY, "Camera not working");
        notificationService.messages.clear();

        service.updateComplaintStatus(complaint.getComplaintId(), ComplaintStatus.RESOLVED);

        assertEquals(ComplaintStatus.RESOLVED, service.getComplaintById(complaint.getComplaintId()).getStatus());
        assertEquals(1, notificationService.messages.size());
        assertTrue(notificationService.messages.get(0).contains("updated to RESOLVED"));
    }

    @Test
    void updateComplaintStatusThrowsWhenComplaintMissing() {
        ComplaintService service = new ComplaintService(new RecordingNotificationService());

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> service.updateComplaintStatus("missing-id", ComplaintStatus.CLOSED)
        );

        assertEquals("Complaint not found", ex.getMessage());
    }

    @Test
    void getComplaintsForResidentReturnsEmptyForUnknownResident() {
        ComplaintService service = new ComplaintService(new RecordingNotificationService());

        List<Complaint> complaints = service.getComplaintsForResident("unknown");

        assertNotNull(complaints);
        assertTrue(complaints.isEmpty());
    }

    @Test
    void registerResidentIsIdempotentForComplaintList() {
        ComplaintService service = new ComplaintService(new RecordingNotificationService());
        Resident resident = new Resident("Rohit", "rohit@gmail.com");

        service.registerResident(resident);
        service.registerResident(resident);
        service.raiseComplaint(resident, ComplaintCategory.OTHER, "General issue");

        assertEquals(1, service.getComplaintsForResident(resident.getResidentId()).size());
    }
}
