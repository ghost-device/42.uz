package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.entity.CodeEntity;
import uz.web.repo.CodeRepo;

import java.time.LocalDateTime;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CodeService extends BaseService<CodeEntity> {
    private final CodeRepo codeRepo;

    public CodeEntity getCodeByEmail(String email){
        return codeRepo.getCodeByEmail(email);
    }

    @Override
    public void save(CodeEntity codeEntity) {
        try {
            CodeEntity code = this.getCodeByEmail(codeEntity.getEmail());

            code.setCode(codeEntity.getCode());
            code.setExpiredAt(LocalDateTime.now().plusMinutes(1));

            this.update(code);
        } catch (Exception e){
            codeRepo.save(codeEntity);
        }
    }

    @Override
    public CodeEntity findById(UUID id) {
        return codeRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        codeRepo.delete(id);
    }

    @Override
    public void update(CodeEntity codeEntity) {
        codeRepo.update(codeEntity);
    }
}
