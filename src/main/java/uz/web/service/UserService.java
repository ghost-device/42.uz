package uz.web.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DTO.UpdatePasswordDTO;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.AuthDTO;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.exceptions.AuthFailedException;
import uz.web.domain.exceptions.EmailNotFoundException;
import uz.web.domain.exceptions.InvalidCodeException;
import uz.web.repo.UserRepo;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService extends BaseService<UserEntity> {
    private final UserRepo userRepo;
    private final UserVerificationService userVerificationService;

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
        UserEntity user;

        try {
            user = userRepo.getUserByEmail(authDTO.getEmail());
        } catch (Exception e) {
            throw new AuthFailedException("Email is incorrect!");
        }

        if (user.getPassword().equals(authDTO.getPassword())) {
            return new UserDao(user.getId(), user.getEmail(), user.getBalance());
        }

        throw new AuthFailedException("Password is incorrect!");
    }


    public void changePassword(UpdatePasswordDTO updatePasswordDTO) {
        UserEntity user = findById(updatePasswordDTO.getId());

        if (user == null) {
            throw new EmailNotFoundException("Email not found!");
        }
        if (!user.getPassword().equals(updatePasswordDTO.getOldPassword())) {
            throw new InvalidCodeException("Old password is incorrect!");
        }

        user.setPassword(updatePasswordDTO.getNewPassword());
        update(user);

    }

    @Transactional
    public void setUserBalanceById(UUID id, Integer amount) {
        userRepo.setBalanceById(id, amount);
    }

    public void checkEmail(String email) {
        userRepo.getUserByEmail(email);
    }

    public UserEntity getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);

    }

    @Override
    public void save(UserEntity userEntity) {
        userRepo.save(userEntity);
    }

    @Override
    public UserEntity findById(UUID id) {
        return userRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        userRepo.delete(id);
    }

    @Override
    public void update(UserEntity userEntity) {
        userRepo.update(userEntity);
    }
}