package uz.web.service;

import org.springframework.stereotype.Service;
import uz.web.domain.entity.CodeEntity;
import uz.web.repo.CodeRepo;

@Service
public class CodeService extends BaseService<CodeEntity, CodeRepo> {
    public CodeEntity getCodeByEmail(String email){
        return repo.getCodeByEmail(email);
    }
}
