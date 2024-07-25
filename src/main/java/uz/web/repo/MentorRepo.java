package uz.web.repo;

import org.springframework.stereotype.Repository;
import uz.web.domain.DAO.MentorDAO;

import uz.web.domain.entity.MentorEntity;

import java.util.List;

@Repository
public class MentorRepo extends BaseRepo<MentorEntity> {

    public List<MentorDAO> getAllMentors() {
        return entityManager.createQuery("select new uz.web.domain.DAO.MentorDAO(m.name,m.id, m.biography) FROM MentorEntity m"
                , MentorDAO.class).getResultList();
    }
}
