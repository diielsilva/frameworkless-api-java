package com.diel.domain.repositories;

import com.diel.domain.models.Employee;

import java.util.List;
import java.util.Optional;

public interface GenericRepository<T> {
    List<T> findAll();

    Optional<T> findById(Long id);

    Employee save(T employee);

    void delete(Long id);

    Long generateId();
}
