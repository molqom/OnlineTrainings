package by.epam.web.mapper;

import by.epam.web.data.entity.Role;
import by.epam.web.data.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User map(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String login = resultSet.getString("login");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        Role role = Role.valueOf(resultSet.getString("roles.name").toUpperCase());

        return new User(id, login, password, name, surname, role);
    }

}


