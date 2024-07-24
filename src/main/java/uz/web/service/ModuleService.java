package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.ModuleDAO;
import uz.web.domain.entity.ModuleEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ModuleService implements BaseService<ModuleEntity> {
    private final CourseService courseService;

    public List<ModuleDAO> getModulesOfCourse(UUID courseId){
        List<ModuleDAO> list = new ArrayList<>();

        for (ModuleEntity module : courseService.findById(courseId).getModuleEntities()) {
            list.add(new ModuleDAO(module.getId(), module.getName(), module.getDescription(), module.getOrderNum()));
        }

        return list;
    }

    @Override
    public void save(ModuleEntity moduleEntity) {

    }

    @Override
    public ModuleEntity findById(UUID id) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

    @Override
    public void update(ModuleEntity moduleEntity) {

    }
}
