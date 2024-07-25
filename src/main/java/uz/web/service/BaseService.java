package uz.web.service;

import org.springframework.transaction.annotation.Transactional;
import uz.web.domain.entity.BaseEntity;

import java.util.UUID;

public abstract class BaseService<T extends BaseEntity> {
    @Transactional
    public abstract void save(T t);

    public abstract T findById(UUID id);

    @Transactional
    public abstract void delete(UUID id);

    @Transactional
    public abstract void update(T t);
}
