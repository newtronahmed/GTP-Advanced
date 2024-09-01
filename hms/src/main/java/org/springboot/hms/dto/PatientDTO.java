package org.springboot.hms.dto;

import jakarta.annotation.Nullable;
import lombok.Data;

@Data
public class PatientDTO {
    @Nullable
    private String id;
    @Nullable
    private String patientNumber;
    private String surname;
    private String firstName;
    private String address;
    private String phoneNumber;
    // Add any other fields you want to expose in the API
}