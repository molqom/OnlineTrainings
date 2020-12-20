package by.epam.web.dao;

import by.epam.web.data.entity.Course;
import by.epam.web.exception.DaoException;
import by.epam.web.mapper.CountRowMapper;
import by.epam.web.mapper.CourseRowMapper;
import by.epam.web.mapper.RowMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CourseDaoImpl extends AbstractDao<Course> implements CourseDao {
    private static final String TABLE_NAME = "courses";
    private static final String FIND_COURSE_QUANTITY =
            "SELECT COUNT(*) as count FROM courses;";
    private static final String FIND_COURSES_ON_CURRENT_PAGE =
            "select courses.id, courses.name, users.name from courses left join users " +
                    "on courses.teacher_id = users.id limit ? offset ?;";

    protected CourseDaoImpl(Connection connection) {
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
        List<Integer> count = executeQuery(FIND_COURSE_QUANTITY,
                new CountRowMapper());
        return count.get(0);
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

    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void removeById(long id) {

    }
}
