package by.epam.web.dao;

import by.epam.web.data.entity.Course;
import by.epam.web.exception.DaoException;

import java.util.List;

public interface CourseDao extends Dao<Course>{
    List<Course> createList(int numOfPage, int courseQuantityOnPage) throws DaoException;
    int courseQuantity() throws DaoException;
}
