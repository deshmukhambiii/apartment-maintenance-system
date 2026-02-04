package com.apartment.maintenance.domain.model;
import java.util.UUID;

public class Resident {
    private final String residentId;
    private final String name;
    private final String email;

    public Resident(String name, String email) {
        this.residentId = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
    }

    public String getResidentId() {
        return residentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}

