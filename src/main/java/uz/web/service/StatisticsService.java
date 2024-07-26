package uz.web.service;

import org.springframework.stereotype.Service;
import uz.web.domain.DAO.StatisticsDAO;

@Service
public class StatisticsService {
    private CourseService courseService;
    private MentorService mentorService;
    private CourseOfUsersService courseOfUsersService;



    public StatisticsDAO getStatistics(){
        StatisticsDAO statisticsDAO = new StatisticsDAO();

        statisticsDAO.setCourses(courseService.getAllCourse(true).size());
        statisticsDAO.setMentors(mentorService.getAllMentors().size());
        statisticsDAO.setUsersCourses(courseOfUsersService.getAllCoursesOfUsers().size());

        return statisticsDAO;
    }
}


