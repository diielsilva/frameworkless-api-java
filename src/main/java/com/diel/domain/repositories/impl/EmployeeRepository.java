package com.diel.domain.repositories.impl;

import com.diel.domain.exceptions.ModelNotFoundException;
import com.diel.domain.models.Employee;
import com.diel.domain.repositories.GenericRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository implements GenericRepository<Employee> {
    private final List<Employee> database = new ArrayList<>();

    @Override
    public List<Employee> findAll() {
        return this.database;
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Optional<Employee> employee;
        for (int i = 0; i < this.database.size(); i++) {
            if (this.database.get(i).getId().equals(id)) {
                employee = Optional.of(this.database.get(i));
                return employee;
            }
        }
        employee = Optional.empty();
        return employee;
    }

    @Override
    public Employee save(Employee employee) {
        if (employee.getId() != null) {
            for (int i = 0; i < this.database.size(); i++) {
                if (this.database.get(i).getId().equals(employee.getId())) {
                    this.database.get(i).setName(employee.getName());
                    return employee;
                }
            }
            throw new ModelNotFoundException("Employee with ID = " + employee.getId() + " not found");
        } else {
            employee.setId(this.generateId());
            this.database.add(employee);
            return employee;
        }
    }

    @Override
    public void delete(Long id) {
        for (int i = 0; i < this.database.size(); i++) {
            if (this.database.get(i).getId().equals(id)) {
                this.database.remove(i);
                return;
            }
        }
        throw new ModelNotFoundException("Employee with ID = " + id + " not found");
    }

    @Override
    public Long generateId() {
        if (this.database.isEmpty()) {
            return 1L;
        } else {
            return this.database.get(this.database.size() - 1).getId() + 1;
        }
    }
}
