package com.apartment.maintenance.service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import com.apartment.maintenance.domain.enums.ComplaintCategory;
import com.apartment.maintenance.domain.enums.ComplaintStatus;
import com.apartment.maintenance.domain.model.Complaint;
import com.apartment.maintenance.domain.model.Resident;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ComplaintService {

    private final Map<String, Complaint> complaintStore = new ConcurrentHashMap<>();
    private final NotificationService notificationService;

    public ComplaintService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public Complaint raiseComplaint(Resident resident,
                                    ComplaintCategory category,
                                    String description) {

        Complaint complaint = new Complaint(resident, category, description);
        complaintStore.put(complaint.getComplaintId(), complaint);

        notificationService.notifyResident(
                resident,
                "Complaint registered with ID: " + complaint.getComplaintId()
        );

        return complaint;
    }

    public void updateComplaintStatus(String complaintId, ComplaintStatus newStatus) {
        Complaint complaint = complaintStore.get(complaintId);

        if (complaint == null) {
            throw new IllegalArgumentException("Complaint not found");
        }

        complaint.updateStatus(newStatus);

        notificationService.notifyResident(
                complaint.getResident(),
                "Complaint " + complaintId + " updated to " + newStatus
        );
    }

    public Complaint getComplaintById(String complaintId) {
        return complaintStore.get(complaintId);
    }

    public List<Complaint> getComplaintsForResident(String residentId) {
        List<Complaint> result = new ArrayList<>();
        for (Complaint complaint : complaintStore.values()) {
            if (complaint.getResident().getResidentId().equals(residentId)) {
                result.add(complaint);
            }
        }
        return result;
    }
}

