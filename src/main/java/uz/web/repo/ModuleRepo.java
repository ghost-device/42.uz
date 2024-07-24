package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.DAO.ModuleDAO;
import uz.web.domain.entity.ModuleEntity;

import java.util.List;
import java.util.UUID;

@Repository
public class ModuleRepo extends BaseRepo<ModuleEntity> {
    public List<ModuleDAO> getModulesOfCourse(UUID courseId){
        return entityManager
                .createQuery("select new uz.web.domain.DAO.ModuleDAO(m.id, m.name, m.description, m.orderNum) from ModuleEntity m where m.course.id = :cId order by m.orderNum", ModuleDAO.class)
                .getResultList();
    }
}
