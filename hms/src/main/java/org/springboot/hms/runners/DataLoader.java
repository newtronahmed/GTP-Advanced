//package org.springboot.hms.runners;
//
////import jakarta.transaction.Transactional;
//
//import org.springboot.hms.models.*;
//import org.springboot.hms.repositories.*;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDate;
//
//@Configuration
//public class DataLoader {
//
//    @Bean
//    public ApplicationRunner loadData(
//            DepartmentRepository departmentRepository,
//            DoctorRepository doctorRepository,
//            NurseRepository nurseRepository,
//            PatientRepository patientRepository,
//            WardRepository wardRepository,
//            HospitalizationRepository hospitalizationRepository) {
//
//        return args -> {
//            try {
//                loadDataInTransaction(departmentRepository, doctorRepository, nurseRepository,
//                        patientRepository, wardRepository, hospitalizationRepository);
//            } catch (Exception e) {
//                // Log the exception if needed
//                throw new RuntimeException("Error occurred while loading data", e); // Re-throw as unchecked to ensure rollback
//            }
//        };
//    }
//
//    @Transactional
//    public void loadDataInTransaction(
//            DepartmentRepository departmentRepository,
//            DoctorRepository doctorRepository,
//            NurseRepository nurseRepository,
//            PatientRepository patientRepository,
//            WardRepository wardRepository,
//            HospitalizationRepository hospitalizationRepository) {
//
//        // Create and save Department
//        Department department = new Department();
//        department.setCode("CARD2");
//        department.setName("Cardiology");
//        department.setBuilding("Main Building");
//        departmentRepository.save(department);
//
//        // Create and save Doctor
//        Doctor doctor = new Doctor();
//        doctor.setFirstName("John");
//        doctor.setSurname("Doe");
//        doctor.setEmployeeNumber("D001");
//        doctor.setSpeciality("Cardiologist");
//        doctor.setDirectorDepartment(department);
//        doctorRepository.save(doctor);
//
//        // Assign the doctor as the department director
//        department.setDirector(doctor);
//        departmentRepository.save(department);
//
//        // Create and save Nurse
//        Nurse nurse = new Nurse();
//        nurse.setFirstName("Alice");
//        nurse.setSurname("Smith");
//        nurse.setEmployeeNumber("N001");
//        nurse.setRotation("Day");
//        nurse.setSalary(5000);
//        nurse.setAssignedDepartment(department);
//        nurseRepository.save(nurse);
//
//        // Create and save Ward
//        Ward ward = new Ward();
//        ward.setNumber(101);
//        ward.setNumberOfBeds(10);
//        ward.setDepartment(department);
//        ward.setSupervisor(nurse);
//        wardRepository.save(ward);
//
//        // Create and save Patient
//        Patient patient = new Patient();
//        patient.setFirstName("Mark");
//        patient.setSurname("Jones");
//        patient.setPatientNumber("P001");
//        patientRepository.save(patient);
//
//        // Create and save Hospitalization
//        Hospitalization hospitalization = new Hospitalization();
//        hospitalization.setPatient(patient);
//        hospitalization.setWard(ward);
//        hospitalization.setBedNumber(1);
//        hospitalization.setDoctor(doctor);
//        hospitalization.setDiagnosis("Heart Disease");
//        hospitalization.setAdmissionDate(LocalDate.now());
//        hospitalizationRepository.save(hospitalization);
//    }
//}
