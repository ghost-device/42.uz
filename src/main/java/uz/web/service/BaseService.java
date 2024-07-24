package uz.web.service;

import uz.web.domain.entity.BaseEntity;

import java.util.UUID;

public interface BaseService<T extends BaseEntity> {
    void save(T t);

    T findById(UUID id);

    void delete(UUID id);

    void update(T t);
}
