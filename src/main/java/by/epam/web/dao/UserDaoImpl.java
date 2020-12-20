package by.epam.web.dao;

import by.epam.web.data.entity.User;
import by.epam.web.exception.DaoException;
import by.epam.web.mapper.RowMapper;
import by.epam.web.mapper.UserRowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {
    //private static final String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE user_name = ? AND user_pswd = sha1(?)";
    private static final String FIND_BY_LOGIN_AND_PASSWORD =
            "SELECT users.id, users.login, users.password, users.name, users.surname, roles.name FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE login = ? AND password = sha1(?)";
    private static final String TABLE_NAME = "users";
    private static final String SAVE_USER = "INSERT users(login, password, name, surname) VALUES(?, sha1(?), ?, ?)";

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {

        return executeForSingleResult(
                FIND_BY_LOGIN_AND_PASSWORD,
                new UserRowMapper(),
                login,
                password
        );
    }

    @Override
    public boolean isUserExist(String userName) {
        return false;
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.empty();
    }

    @Override
    public void save(User item) throws DaoException {
        String login = item.getLogin();
        String password = item.getPassword();
        String name = item.getName();
        String surname = item.getSurname();


        execute(SAVE_USER, login, password, name, surname);
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery("SELECT * FROM " + getTableName(), new UserRowMapper());
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }
}
