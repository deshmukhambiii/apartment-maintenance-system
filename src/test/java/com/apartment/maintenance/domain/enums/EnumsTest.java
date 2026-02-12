package com.apartment.maintenance.domain.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnumsTest {

    @Test
    void complaintCategoryHasExpectedValues() {
        List<ComplaintCategory> values = Arrays.asList(ComplaintCategory.values());

        assertEquals(
                Arrays.asList(
                        ComplaintCategory.PLUMBING,
                        ComplaintCategory.ELECTRICAL,
                        ComplaintCategory.SECURITY,
                        ComplaintCategory.CLEANING,
                        ComplaintCategory.OTHER
                ),
                values
        );
    }

    @Test
    void complaintStatusHasExpectedValues() {
        List<ComplaintStatus> values = Arrays.asList(ComplaintStatus.values());

        assertEquals(
                Arrays.asList(
                        ComplaintStatus.OPEN,
                        ComplaintStatus.IN_PROGRESS,
                        ComplaintStatus.RESOLVED,
                        ComplaintStatus.CLOSED
                ),
                values
        );
    }
}
