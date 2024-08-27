//package org.springboot.hms.models;
//
//import jakarta.persistence.*;
//import lombok.Data;
//import java.time.LocalDate;
//
//@Entity
//@Data
//public class Hospitalization {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long hospitalizationId;
//
//    private Integer bedNumber;
//    private String diagnosis;
//    private LocalDate admissionDate;
//    private LocalDate dischargeDate;
//
//    @ManyToOne
//    @JoinColumn(name = "patient_number")
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumns({
//            @JoinColumn(name = "ward_number", referencedColumnName = "wardNumber"),
//            @JoinColumn(name = "department_code", referencedColumnName = "departmentCode")
//    })
//    private Ward ward;
//
//    @ManyToOne
//    @JoinColumn(name = "doctor_employee_number")
//    private Doctor treatingDoctor;
//}
