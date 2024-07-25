package uz.web.service;

import uz.web.domain.entity.BaseEntity;

import java.util.UUID;

public abstract class BaseService<T extends BaseEntity> {
    public abstract void save(T t);

    public abstract T findById(UUID id);

    public abstract void delete(UUID id);

    public abstract void update(T t);
}
