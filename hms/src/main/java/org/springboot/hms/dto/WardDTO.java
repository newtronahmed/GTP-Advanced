package org.springboot.hms.dto;

import lombok.Data;
import org.springboot.hms.models.Department;

@Data
public class WardDTO {
    private int number;
    private int numberOfBeds;
    private Department department;
    private int availableBeds;
}
