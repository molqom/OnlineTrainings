package by.epam.web.dao.course;

import by.epam.web.dao.Dao;
import by.epam.web.entity.Course;
import by.epam.web.exception.DaoException;

import java.util.List;

public interface CourseDao extends Dao<Course> {
    List<Course> createList(int numOfPage, int courseQuantityOnPage) throws DaoException;
    int courseQuantity() throws DaoException;
    List<Course> findCoursesByTeacherId(long teacherId) throws DaoException;
}
