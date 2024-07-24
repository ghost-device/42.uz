package uz.web.service;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.StatisticDAO;

@Service
public class StatisticService {

    CourseService courseService;
    MentorService mentorService;
    CourseOfUserService courseOfUserService;

    public StatisticDAO getStatistics(){
        return StatisticDAO.builder()
                .courseNumber(courseService.coursesNumber())
                .mentorNumber(mentorService.mentorNumber())
                .studentNumber(courseOfUserService.userCourses())
                .build();
    }
}



