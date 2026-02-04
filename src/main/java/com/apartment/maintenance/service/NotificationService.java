package com.apartment.maintenance.service;

import com.apartment.maintenance.domain.model.Notification;
import com.apartment.maintenance.domain.model.Resident;

public class NotificationService {

    public void notifyResident(Resident resident, String message) {
        Notification notification = new Notification(message);
        // In real life: email / SMS / push
        System.out.println("Notification to " + resident.getEmail() + ": " + notification);
    }
}

