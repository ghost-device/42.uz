package uz.web.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.UserDao;
import uz.web.domain.DTO.AuthDTO;
import uz.web.domain.entity.UserEntity;
import uz.web.domain.exceptions.AuthFailedException;
import uz.web.repo.UserRepo;

import java.util.UUID;

@Service
public class UserService extends BaseService<UserEntity, UserRepo> {
    @Transactional
    public void register(AuthDTO registerDTO){
        try {
            super.save(UserEntity.builder()
                    .email(registerDTO.getEmail())
                    .password(registerDTO.getPassword())
                    .build());
        } catch (Exception e){
            throw new AuthFailedException("This email already used!");
        }
    }

    public UserDao login(AuthDTO authDTO){
        UserEntity user = null;

        try {
            user = repo.getUserByEmail(authDTO.getEmail());
        } catch (Exception e){
            throw new AuthFailedException("Email is incorrect!");
        }

        if (user.getPassword().equals(authDTO.getPassword())){
            return new UserDao(user.getId(), user.getEmail(), user.getBalance());
        }

        throw new AuthFailedException("Password is incorrect!");
    }

    public void setUserBalanceById(UUID id, Integer amount){
        repo.setBalanceById(id, amount);
    }
}
