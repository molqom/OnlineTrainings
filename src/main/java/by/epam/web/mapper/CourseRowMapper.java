package by.epam.web.mapper;

import by.epam.web.entity.Course;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String teacherName = resultSet.getString("users.name");
        String teacherSurname = resultSet.getString("users.surname");
        return new Course(id, name, teacherName, teacherSurname);
    }

    public int calculateCourseQuantity(ResultSet resultSet) throws SQLException{
        return resultSet.getInt("count");
    }
}
