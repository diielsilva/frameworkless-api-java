package com.diel.common.mappers.impl;

import com.diel.common.dtos.employee.DisplayEmployee;
import com.diel.common.dtos.employee.SaveEmployee;
import com.diel.common.mappers.ModelMapper;
import com.diel.domain.models.Employee;

public class ModelMapperImpl implements ModelMapper {
    @Override
    public Employee toEmployee(SaveEmployee saveEmployee) {
        Employee model = new Employee();
        model.setName(saveEmployee.getName());
        return model;
    }

    @Override
    public DisplayEmployee toDisplayEmployee(Employee employee) {
        DisplayEmployee dto = new DisplayEmployee();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        return dto;
    }
}
