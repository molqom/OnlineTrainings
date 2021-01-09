package by.epam.web.dao.course;

import by.epam.web.dao.AbstractDao;
import by.epam.web.entity.Course;
import by.epam.web.enums.Sql;
import by.epam.web.exception.DaoException;
import by.epam.web.mapper.CountRowMapper;
import by.epam.web.mapper.CourseRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {
    private static final String TABLE_NAME = "courses";

    public CourseDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Course> createList(int numOfPage, int courseQuantityOnPage) throws DaoException {
        int numberFirstCourseOnPage = (numOfPage - 1) * courseQuantityOnPage;
        return executeQuery(Sql.FIND_COURSES_ON_CURRENT_PAGE.getQuery(),
                new CourseRowMapper(),
                courseQuantityOnPage,
                numberFirstCourseOnPage);
    }

    @Override
    public int courseQuantity() throws DaoException {
        List<Integer> count = executeQuery(
                Sql.FIND_COURSE_QUANTITY.getQuery(),
                new CountRowMapper());
        return count.get(0);
    }

    @Override
    public List<Course> findCoursesByTeacherId(long teacherId) throws DaoException {
        return executeQuery(
                Sql.FIND_COURSES_FOR_CURRENT_TEACHER.getQuery(),
                new CourseRowMapper(),
                teacherId);
    }

    @Override
    public Optional<Course> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Course> getAll() throws DaoException {
        return null;
    }

    @Override
    public void save(Course item) throws DaoException {
        String name = item.getName();
        long teacherId = item.getTeacherId();

        execute(
                Sql.ADD_COURSE.getQuery(),
                name,
                teacherId);
    }

    @Override
    protected String getTableName() {
        return Sql.TABLE_COURSES.getQuery();
    }

    @Override
    public void removeById(long id) throws DaoException {
        execute(
                Sql.DELETE_COURSE.getQuery(),
                id);
    }
}
