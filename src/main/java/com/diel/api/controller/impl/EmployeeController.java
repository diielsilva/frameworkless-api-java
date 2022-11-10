package com.diel.api.controller.impl;

import com.diel.api.controller.GenericController;
import com.diel.common.dtos.employee.DisplayEmployee;
import com.diel.common.dtos.employee.SaveEmployee;
import com.diel.domain.models.Employee;
import com.diel.domain.services.GenericService;

public class EmployeeController extends GenericController<DisplayEmployee, SaveEmployee, Employee> {

    public EmployeeController(GenericService<DisplayEmployee, SaveEmployee, Employee> service) {
        super(service);
    }
}
