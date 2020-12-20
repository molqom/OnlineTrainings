package by.epam.web.service;

import by.epam.web.dao.CourseDao;
import by.epam.web.dao.DaoHelper;
import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.data.entity.Course;
import by.epam.web.exception.DaoException;

import java.sql.SQLException;
import java.util.List;

public class TrainingsService {
    private DaoHelperFactory factory;

    public TrainingsService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public List<Course> createListOfCourses(int numOfPage, int courseQuantityOnPage) throws DaoException {
        try (DaoHelper daoHelper = factory.create()) {
            CourseDao dao = daoHelper.createCourseDao();
            return dao.createList(numOfPage, courseQuantityOnPage);
        } catch (SQLException | DaoException throwables) {
            throw new DaoException("oops this is trouble...");
        }
    }
    public int calculatePagesQuantity(int courseQuantityOnPage) throws DaoException {
        try (DaoHelper daoHelper = factory.create()) {
            CourseDao dao = daoHelper.createCourseDao();
            int courseQuantity = dao.courseQuantity();
            int pagesQuantity = (int)Math.ceil((double)courseQuantity / courseQuantityOnPage);
            return pagesQuantity;
        } catch (SQLException | DaoException throwables) {
            throw new DaoException("oops this is trouble...");
        }
    }

}
