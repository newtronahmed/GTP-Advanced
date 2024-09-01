package org.springboot.hms.dto;

import lombok.Data;

@Data
public class CreatePatientDTO {
    private String surname;
    private String firstName;
    private String address;
    private String phoneNumber;
}
