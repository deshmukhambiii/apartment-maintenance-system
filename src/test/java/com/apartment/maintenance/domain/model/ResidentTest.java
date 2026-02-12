package com.apartment.maintenance.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResidentTest {

    @Test
    void constructorSetsFieldsAndGeneratesId() {
        Resident resident = new Resident("Amit Sharma", "amit@gmail.com");

        assertNotNull(resident.getResidentId());
        assertFalse(resident.getResidentId().isBlank());
        assertEquals("Amit Sharma", resident.getName());
        assertEquals("amit@gmail.com", resident.getEmail());
    }
}
