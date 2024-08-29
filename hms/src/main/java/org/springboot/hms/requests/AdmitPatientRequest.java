package org.springboot.hms.requests;

import lombok.Data;

@Data
public class AdmitPatientRequest {
    private Long patientId;
    private Long wardId;
    private int bedNumber;
    private String diagnosis;
    private Long doctorId;

    // Getters and Setters
}
