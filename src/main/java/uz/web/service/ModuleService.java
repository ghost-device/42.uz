package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.ModuleDAO;
import uz.web.domain.entity.ModuleEntity;
import uz.web.repo.CourseRepo;
import uz.web.repo.ModuleRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService extends BaseService<ModuleEntity> {
    private final CourseService courseService;
    private final ModuleRepo moduleRepo;

    public List<ModuleDAO> getModulesOfCourse(UUID courseId){
        List<ModuleDAO> list = new ArrayList<>();

        for (ModuleEntity module : courseService.findById(courseId).getModuleEntities()) {
            list.add(new ModuleDAO(module.getId(), module.getName(), module.getDescription(), module.getOrderNum()));
        }

        return list;
    }

    @Override
    public void save(ModuleEntity moduleEntity) {
        moduleRepo.save(moduleEntity);
    }

    @Override
    public ModuleEntity findById(UUID id) {
        return moduleRepo.findById(id);
    }

    @Override
    public void delete(UUID id) {
        moduleRepo.delete(id);
    }

    @Override
    public void update(ModuleEntity moduleEntity) {
        moduleRepo.update(moduleEntity);
    }
}
