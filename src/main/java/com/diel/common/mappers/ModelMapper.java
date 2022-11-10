package com.diel.common.mappers;

import com.diel.common.dtos.employee.DisplayEmployee;
import com.diel.common.dtos.employee.SaveEmployee;
import com.diel.domain.models.Employee;

public interface ModelMapper {
    Employee toEmployee(SaveEmployee saveEmployee);

    DisplayEmployee toDisplayEmployee(Employee employee);
}
