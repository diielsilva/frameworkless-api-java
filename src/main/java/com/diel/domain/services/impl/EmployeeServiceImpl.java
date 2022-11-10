package com.diel.domain.services.impl;

import com.diel.common.dtos.employee.DisplayEmployee;
import com.diel.common.dtos.employee.SaveEmployee;
import com.diel.common.mappers.ModelMapper;
import com.diel.domain.exceptions.ModelNotFoundException;
import com.diel.domain.models.Employee;
import com.diel.domain.repositories.GenericRepository;
import com.diel.domain.services.GenericService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements GenericService<DisplayEmployee, SaveEmployee, Employee> {
    private final GenericRepository<Employee> repository;
    private final ModelMapper modelMapper;

    public EmployeeServiceImpl(GenericRepository<Employee> repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<DisplayEmployee> findAll() {
        List<Employee> employees = this.repository.findAll();
        return employees.stream().map(this.modelMapper::toDisplayEmployee).collect(Collectors.toList());
    }

    @Override
    public DisplayEmployee findById(Long id) {
        Employee employee = this.findByIdOrThrowModelNotFoundException(id);
        return this.modelMapper.toDisplayEmployee(employee);
    }

    @Override
    public DisplayEmployee save(SaveEmployee saveEmployee) {
        Employee model = this.modelMapper.toEmployee(saveEmployee);
        model = this.repository.save(model);
        return this.modelMapper.toDisplayEmployee(model);
    }

    @Override
    public DisplayEmployee update(Long id, SaveEmployee saveEmployee) {
        Employee model = this.modelMapper.toEmployee(saveEmployee);
        Employee databaseModel = this.findByIdOrThrowModelNotFoundException(id);
        this.setUpValues(databaseModel, model);
        return this.modelMapper.toDisplayEmployee(databaseModel);
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(id);
    }

    private Employee findByIdOrThrowModelNotFoundException(Long id) {
        Optional<Employee> databaseEmployee = this.repository.findById(id);
        if (databaseEmployee.isEmpty()) {
            throw new ModelNotFoundException("Employee with ID = " + id + " not found");
        }
        return databaseEmployee.get();
    }

    private void setUpValues(Employee databaseEmployee, Employee employeeToUpdate) {
        databaseEmployee.setName(employeeToUpdate.getName());
    }
}
