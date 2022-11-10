package com.diel.api.controller;

import com.diel.common.dtos.standard.ResponseEntity;
import com.diel.domain.enums.HttpStatus;
import com.diel.domain.exceptions.ModelNotFoundException;
import com.diel.domain.services.GenericService;

import java.util.List;

public abstract class GenericController<Display, Save, Entity> {

    private final GenericService<Display, Save, Entity> service;

    protected GenericController(GenericService<Display, Save, Entity> service) {
        this.service = service;
    }

    public ResponseEntity<List<Display>> findAll() {
        List<Display> displays = this.service.findAll();
        return new ResponseEntity<>(HttpStatus.OK, displays);
    }

    public ResponseEntity<Display> findById(Long id) throws ModelNotFoundException {
        Display display = this.service.findById(id);
        return new ResponseEntity<>(HttpStatus.OK, display);
    }

    public ResponseEntity<Display> save(Save save) {
        Display display = this.service.save(save);
        return new ResponseEntity<>(HttpStatus.CREATED, display);
    }

    public ResponseEntity<Display> update(Long id, Save save) {
        Display display = this.service.update(id, save);
        return new ResponseEntity<>(HttpStatus.OK, display);
    }

    public ResponseEntity<String> delete(Long id) {
        this.service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT, "");
    }

}
