package by.epam.web.mapper;

import by.epam.web.entity.Subscription;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubscriptionRowMapper implements RowMapper<Subscription> {
    @Override
    public Subscription map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        long userId = resultSet.getLong("user_id");
        String userName = resultSet.getString("user_name");
        String userSurname = resultSet.getString("user_surname");
        long courseId = resultSet.getLong("course_id");
        String courseName = resultSet.getString("course_name");
        String teacherName = resultSet.getString("teacher_name");
        String teacherSurname = resultSet.getString("teacher_surname");
        int grade = resultSet.getInt("grade");
        String feedback = resultSet.getString("feedback");
        return new Subscription(
                id,
                userId,
                userName,
                userSurname,
                courseId,
                courseName,
                teacherName,
                teacherSurname,
                grade,
                feedback);
    }
}
