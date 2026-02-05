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
        Resident residentTwo = new Resident("Priya Desai", "priya@gmail.com");
        Resident residentThree = new Resident("Rohit Verma", "rohit@gmail.com");
        Resident residentFour = new Resident("Sneha Iyer", "sneha@gmail.com");
        Resident residentFive = new Resident("Vikram Singh", "vikram@gmail.com");

        complaintService.registerResident(resident);
        complaintService.registerResident(residentTwo);
        complaintService.registerResident(residentThree);
        complaintService.registerResident(residentFour);
        complaintService.registerResident(residentFive);

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

        System.out.println("All Complaints:");
        complaintService
                .getAllComplaints()
                .forEach(System.out::println);
    }
}
