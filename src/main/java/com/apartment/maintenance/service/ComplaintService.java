package com.apartment.maintenance.service;

import com.apartment.maintenance.domain.enums.ComplaintCategory;
import com.apartment.maintenance.domain.enums.ComplaintStatus;
import com.apartment.maintenance.domain.model.Complaint;
import com.apartment.maintenance.domain.model.Resident;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ComplaintService {

    private final Map<String, Complaint> complaintStore = new ConcurrentHashMap<>();
    private final Map<String, Resident> residentStore = new ConcurrentHashMap<>();
    private final Map<String, List<Complaint>> residentComplaintStore = new ConcurrentHashMap<>();
    private final NotificationService notificationService;

    public ComplaintService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void registerResident(Resident resident) {
        residentStore.put(resident.getResidentId(), resident);
        residentComplaintStore.putIfAbsent(resident.getResidentId(), new ArrayList<>());
    }

    public Complaint raiseComplaint(Resident resident,
                                    ComplaintCategory category,
                                    String description) {
        if (!residentStore.containsKey(resident.getResidentId())) {
            registerResident(resident);
        }

        Complaint complaint = new Complaint(resident, category, description);
        complaintStore.put(complaint.getComplaintId(), complaint);
        residentComplaintStore
                .computeIfAbsent(resident.getResidentId(), key -> new ArrayList<>())
                .add(complaint);

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
        return residentComplaintStore.getOrDefault(residentId, Collections.emptyList());
    }
}
