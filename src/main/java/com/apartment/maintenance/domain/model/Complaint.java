package com.apartment.maintenance.domain.model;

import com.apartment.maintenance.domain.enums.ComplaintCategory;
import com.apartment.maintenance.domain.enums.ComplaintStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class Complaint {
    private final String complaintId;
    private final Resident resident;
    private final ComplaintCategory category;
    private final String description;
    private ComplaintStatus status;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Complaint(Resident resident, ComplaintCategory category, String description) {
        this.complaintId = UUID.randomUUID().toString();
        this.resident = resident;
        this.category = category;
        this.description = description;
        this.status = ComplaintStatus.OPEN;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    public String getComplaintId() {
        return complaintId;
    }

    public Resident getResident() {
        return resident;
    }

    public ComplaintCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public ComplaintStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void updateStatus(ComplaintStatus newStatus) {
        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id='" + complaintId + '\'' +
                ", category=" + category +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }
}
