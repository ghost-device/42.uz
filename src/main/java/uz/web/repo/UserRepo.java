package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.UserEntity;

import java.util.UUID;

@Repository
public class UserRepo extends BaseRepo<UserEntity> {
    public UserRepo() {
        super.aClass = UserEntity.class;
    }

    public UserEntity getUserByEmail(String email) {
        return entityManager.createQuery("from UserEntity u where u.email = :e", UserEntity.class)
                .setParameter("e", email)
                .getSingleResult();
    }

    public void setBalanceById(UUID userId, Integer amount) {
        UserEntity user = entityManager.find(UserEntity.class, userId);

        user.setBalance(user.getBalance() + amount);

        entityManager.merge(user);
    }
}