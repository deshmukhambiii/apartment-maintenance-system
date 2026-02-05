package com.apartment.maintenance.app;

import com.apartment.maintenance.domain.enums.ComplaintCategory;
import com.apartment.maintenance.domain.enums.ComplaintStatus;
import com.apartment.maintenance.domain.model.Complaint;
import com.apartment.maintenance.domain.model.Resident;
import com.apartment.maintenance.service.ComplaintService;
import com.apartment.maintenance.service.NotificationService;

public class ApartmentMaintenanceApp {

    public static void main(String[] args) {

        NotificationService notificationService = new NotificationService();
        ComplaintService complaintService = new ComplaintService(notificationService);

        Resident resident = new Resident("Amit Sharma", "amit@gmail.com");
        complaintService.registerResident(resident);

        Complaint complaint = complaintService.raiseComplaint(
                resident,
                ComplaintCategory.PLUMBING,
                "Water leakage in bathroom"
        );

        complaintService.updateComplaintStatus(
                complaint.getComplaintId(),
                ComplaintStatus.IN_PROGRESS
        );

        complaintService.updateComplaintStatus(
                complaint.getComplaintId(),
                ComplaintStatus.RESOLVED
        );

        System.out.println("Resident Complaints:");
        complaintService
                .getComplaintsForResident(resident.getResidentId())
                .forEach(System.out::println);
    }
}
