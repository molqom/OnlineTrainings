package by.epam.web.dao.course;

import by.epam.web.dao.AbstractDao;
import by.epam.web.entity.Course;
import by.epam.web.exception.DaoException;
import by.epam.web.mapper.CountRowMapper;
import by.epam.web.mapper.CourseRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {

    public CourseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Course> createList(int numOfPage, int courseQuantityOnPage) throws DaoException {
        int numberFirstCourseOnPage = (numOfPage - 1) * courseQuantityOnPage;
        return executeQuery(FIND_COURSES_ON_CURRENT_PAGE,
                new CourseRowMapper(),
                courseQuantityOnPage,
                numberFirstCourseOnPage);
    }

    @Override
    public int courseQuantity() throws DaoException {
        List<Integer> count = executeQuery(
                FIND_COURSE_QUANTITY,
                new CountRowMapper());
        return count.get(0);
    }

    @Override
    public List<Course> findCoursesByTeacherId(long teacherId) throws DaoException {
        return executeQuery(
                FIND_COURSES_FOR_CURRENT_TEACHER,
                new CourseRowMapper(),
                teacherId);
    }

    @Override
    public Optional<Course> getById(long id) throws DaoException {
        return executeForSingleResult(
                GET_COURSE_BY_ID,
                new CourseRowMapper(),
                id
        );
    }

    @Override
    public List<Course> getAll(int numOfPage, int courseQuantityOnPage) throws DaoException {
        return null;
    }

    @Override
    public void add(Course item) throws DaoException {
        String name = item.getName();
        long teacherId = item.getTeacherId();

        execute(
                ADD_COURSE,
                name,
                teacherId);
    }

    @Override
    protected String getTableName() {
        return TABLE_COURSES;
    }

    @Override
    public void removeById(long id) throws DaoException {
        execute(
                DELETE_COURSE,
                id);
    }
}
