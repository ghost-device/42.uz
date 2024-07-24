package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.entity.CodeEntity;
import uz.web.repo.CodeRepo;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CodeService implements BaseService<CodeEntity> {
    private final CodeRepo repo;

    public CodeEntity getCodeByEmail(String email){
        return repo.getCodeByEmail(email);
    }

    @Override
    public void save(CodeEntity codeEntity) {
        repo.save(codeEntity);
    }

    @Override
    public CodeEntity findById(UUID id) {
        return repo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        repo.delete(id);
    }

    @Override
    public void update(CodeEntity codeEntity) {
        repo.update(codeEntity);
    }
}
