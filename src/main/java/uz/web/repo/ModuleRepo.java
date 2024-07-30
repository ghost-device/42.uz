package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.entity.ModuleEntity;

@Repository
public class ModuleRepo extends BaseRepo<ModuleEntity> {
    public ModuleRepo() {super.aClass = ModuleEntity.class;}

}
