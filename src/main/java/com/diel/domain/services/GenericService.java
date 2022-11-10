package com.diel.domain.services;

import java.util.List;

public interface GenericService<Display, Save, Entity> {
    List<Display> findAll();

    Display findById(Long id);

    Display save(Save save);

    Display update(Long id, Save save);

    void delete(Long id);
}
