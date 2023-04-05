package org.sda.bms.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    void create(T entity);

    void update(T entity);

    void delete(T entity);

    Optional<T> findById(int id);

    List<T> findAll();

}
