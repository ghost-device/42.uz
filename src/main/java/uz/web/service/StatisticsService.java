package uz.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.web.domain.DAO.StatisticsDAO;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final CourseService courseService;
    private final MentorService mentorService;
    private final CourseOfUsersService courseOfUsersService;

    public StatisticsDAO getStatistics(){
        StatisticsDAO statisticsDAO = new StatisticsDAO();

        statisticsDAO.setCourses(courseService.getAllCourse(true).size());
        statisticsDAO.setMentors(mentorService.getAllMentors().size());
        statisticsDAO.setUsersCourses(courseOfUsersService.getAllCoursesOfUsers().size());

        return statisticsDAO;
    }
}


