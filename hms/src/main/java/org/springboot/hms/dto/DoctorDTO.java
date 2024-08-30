package org.springboot.hms.dto;


import lombok.Data;

@Data
public class DoctorDTO {
    private String firstName;
    private String surname;
    private String address;
    private String phoneNumber;
    private String speciality;
}
