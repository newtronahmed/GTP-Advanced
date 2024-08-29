package org.springboot.hms.services;


import jakarta.transaction.Transactional;
import org.springboot.hms.models.Department;
import org.springboot.hms.models.Ward;
import org.springboot.hms.repositories.DepartmentRepository;
import org.springboot.hms.repositories.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private WardRepository wardRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
    @Transactional
    @CacheEvict(value = "departments", allEntries = true)
    public Department createDepartmentWithWards(Department department, List<Ward> wards) {
        Department savedDepartment = departmentRepository.save(department);
        for (Ward ward : wards) {
            ward.setDepartment(savedDepartment);
            wardRepository.save(ward);
        }
        return savedDepartment;
    }
}

