package org.springboot.hms.requests;

import lombok.Data;

@Data
public class TransferPatientRequest {
    private Long newWardId;
    private int newBedNumber;

    // Getters and Setters
}
