package uz.web.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import uz.web.domain.entity.BaseEntity;

import java.util.UUID;

public abstract class BaseRepo<T extends BaseEntity> {
    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> aClass;

    public void save(T t) {
        entityManager.persist(t);
    }

    public T findById(UUID id) {
        return entityManager.find(aClass, id);
    }

    public void delete(UUID id) {
        T entity = findById(id);

        if (entity != null) {
            entityManager.remove(entity);
        } else {
            System.out.println("O'chirish uchun ID " + id + " bo'lgan entiti topilmadi.");
        }
    }

    public void update(T t) {
        entityManager.merge(t);
    }
}
