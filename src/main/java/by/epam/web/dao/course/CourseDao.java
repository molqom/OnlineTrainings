package by.epam.web.dao.course;

import by.epam.web.dao.Dao;
import by.epam.web.entity.Course;
import by.epam.web.exception.DaoException;

import java.util.List;

public interface CourseDao extends Dao<Course> {
    String TABLE_COURSES = "courses";
    String GET_COURSE_BY_ID = "select id, name, teacher_id from courses where id = ?";
    String FIND_COURSE_QUANTITY = "SELECT COUNT(*) as count FROM courses where is_delete = false;";
    String FIND_COURSES_ON_CURRENT_PAGE =
                    "select courses.id, courses.name, users.name, users.surname from courses left join users " +
                    "on courses.teacher_id = users.id where courses.is_delete = false limit ? offset ?;";
    String FIND_COURSES_FOR_CURRENT_TEACHER =
                    "select courses.id, courses.name, users.name from courses left join users " +
                    "on courses.teacher_id = users.id where courses.teacher_id = ? and courses.is_delete = false;";
    String ADD_COURSE = "insert courses (name, teacher_id) values (?, ?);";
    String DELETE_COURSE =
            "update courses c left join subscriptions s on c.id = s.course_id set s.is_delete = true, " +
            "c.is_delete = true where c.id = ?;";

    List<Course> createList(int numOfPage, int courseQuantityOnPage) throws DaoException;

    int courseQuantity() throws DaoException;

    List<Course> findCoursesByTeacherId(long teacherId) throws DaoException;
}
