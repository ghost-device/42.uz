package uz.web.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.AuthDTO;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.exceptions.AuthFailedException;
import uz.web.repo.UserRepo;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService implements BaseService<UserEntity> {
    private final UserRepo repo;

    @Transactional
    public void register(AuthDTO registerDTO) {
        try {
            this.save(UserEntity.builder()
                    .email(registerDTO.getEmail())
                    .password(registerDTO.getPassword())
                    .build());
        } catch (Exception e) {
            throw new AuthFailedException("This email already used!");
        }
    }

    public UserDao login(AuthDTO authDTO) {
        UserEntity user = null;

        try {
            user = repo.getUserByEmail(authDTO.getEmail());
        } catch (Exception e) {
            throw new AuthFailedException("Email is incorrect!");
        }

        if (user.getPassword().equals(authDTO.getPassword())) {
            return new UserDao(user.getId(), user.getEmail(), user.getBalance());
        }

        throw new AuthFailedException("Password is incorrect!");
    }

    @Transactional
    public void setUserBalanceById(UUID id, Integer amount) {
        repo.setBalanceById(id, amount);
    }

    public void checkEmail(String email) {
        repo.getUserByEmail(email);
    }

    @Override
    public void save(UserEntity userEntity) {
        // Implement save logic
    }

    @Override
    public UserEntity findById(UUID id) {
        return null; // Implement findById logic
    }

    @Override
    public void delete(UUID id) {
        // Implement delete logic
    }

    @Override
    public void update(UserEntity userEntity) {
    }
}