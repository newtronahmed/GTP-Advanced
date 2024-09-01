//package org.springboot.hms.services;
//
//
//import org.springboot.hms.models.Hospitalization;
//import org.springboot.hms.models.Patient;
//import org.springboot.hms.models.Ward;
//import org.springboot.hms.models.Doctor;
//import org.springboot.hms.repositories.HospitalizationRepository;
//import org.springboot.hms.repositories.PatientRepository;
//import org.springboot.hms.repositories.WardRepository;
//import org.springboot.hms.repositories.DoctorRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.transaction.support.TransactionTemplate;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Service
//public class HospitalizationService {
//
//    @Autowired
//    private HospitalizationRepository hospitalizationRepository;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    @Autowired
//    private WardRepository wardRepository;
//    @Autowired
//    private final TransactionTemplate transactionTemplate;
//
//    @Autowired
//    private DoctorRepository doctorRepository;
//
//    public HospitalizationService(TransactionTemplate transactionTemplate) {
//        this.transactionTemplate = transactionTemplate;
//    }
//
//    @Cacheable(value = "hospitalizations", key = "#id")
//    public Hospitalization getHospitalizationById(Long id) {
//        return hospitalizationRepository.findById(id).orElse(null);
//    }
//
//    @Cacheable(value = "hospitalizations", key = "'patient_' + #patientId")
//    public List<Hospitalization> getHospitalizationsByPatientId(Long patientId) {
//        return hospitalizationRepository.findByPatientId(patientId);
//    }
//
//    @CacheEvict(value = {"hospitalizations", "patients", "wards"}, allEntries = true)
//    public Hospitalization admitPatient(String patientId, Long wardId, int bedNumber, String diagnosis, Long doctorId) {
//        return transactionTemplate.execute(status -> {
//            try {
//                Patient patient = patientRepository.findById(patientId)
//                        .orElseThrow(() -> new RuntimeException("Patient not found"));
//                Ward ward = wardRepository.findById(wardId)
//                        .orElseThrow(() -> new RuntimeException("Ward not found"));
//                Doctor doctor = doctorRepository.findById(doctorId)
//                        .orElseThrow(() -> new RuntimeException("Doctor not found"));
//
//                if (bedNumber > ward.getNumberOfBeds()) {
//                    throw new RuntimeException("Invalid bed number for the ward");
//                }
//
//                if (ward.getAvailableBeds() <= 0) {
//                    throw new RuntimeException("No available beds in the ward");
//                }
//
//                // Decrement available beds in the ward
//                ward.decrementAvailableBeds();
//                wardRepository.save(ward);  // Save the ward with updated available beds
//
//                Hospitalization hospitalization = new Hospitalization();
//                hospitalization.setPatient(patient);
//                hospitalization.setWard(ward);
//                hospitalization.setBedNumber(bedNumber);
//                hospitalization.setDiagnosis(diagnosis);
//                hospitalization.setDoctor(doctor);
//                hospitalization.setAdmissionDate(LocalDate.now());
//
//                return hospitalizationRepository.save(hospitalization);
//
//            } catch (RuntimeException ex) {
//                status.setRollbackOnly();
//                throw ex;
//            }
//        });
//    }
//    @Transactional
//    @CacheEvict(value = {"hospitalizations", "patients", "wards"}, allEntries = true)
//    public Hospitalization dischargePatient(Long hospitalizationId) {
//        Hospitalization hospitalization = hospitalizationRepository.findById(hospitalizationId)
//                .orElseThrow(() -> new RuntimeException("Hospitalization not found"));
//
//        if (hospitalization.getDischargeDate() != null) {
//            throw new RuntimeException("Patient already discharged");
//        }
//
//        hospitalization.setDischargeDate(LocalDate.now());
//        return hospitalizationRepository.save(hospitalization);
//    }
//
//    @Transactional
//    @CacheEvict(value = {"hospitalizations", "wards"}, allEntries = true)
//    public Hospitalization transferPatient(Long hospitalizationId, Long newWardId, int newBedNumber) {
//        Hospitalization hospitalization = hospitalizationRepository.findById(hospitalizationId)
//                .orElseThrow(() -> new RuntimeException("Hospitalization not found"));
//
//        Ward oldWard = hospitalization.getWard(); // Get the current ward
//        Ward newWard = wardRepository.findById(newWardId)
//                .orElseThrow(() -> new RuntimeException("New ward not found"));
//
//        if (newBedNumber > newWard.getNumberOfBeds()) {
//            throw new RuntimeException("Invalid bed number for the new ward");
//        }
//
//        if (newWard.getAvailableBeds() <= 0) {
//            throw new RuntimeException("No available beds in the new ward");
//        }
//
//        // Increment available beds in the old ward
//        oldWard.incrementAvailableBeds();
//        wardRepository.save(oldWard);
//
//        // Decrement available beds in the new ward
//        newWard.decrementAvailableBeds();
//        wardRepository.save(newWard);
//
//        // Update hospitalization details
//        hospitalization.setWard(newWard);
//        hospitalization.setBedNumber(newBedNumber);
//
//        return hospitalizationRepository.save(hospitalization);
//    }
//}
