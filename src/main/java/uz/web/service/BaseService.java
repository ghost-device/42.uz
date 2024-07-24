package uz.web.service;

import uz.web.domain.entity.BaseEntity;
import uz.web.repo.BaseRepo;
import uz.web.repo.CourseRepo;

import java.util.UUID;

public abstract class BaseService<T extends BaseEntity, R extends BaseRepo<T>> {
    protected R repo;

    public BaseService(CourseRepo repo) {
    }

    public void save(T t){
        repo.save(t);
    }

    public T findById(UUID id){
        return repo.findById(id);
    }

    public void delete(UUID id){
        repo.delete(id);
    }

    public void update(T t){
        repo.update(t);
    }
}
