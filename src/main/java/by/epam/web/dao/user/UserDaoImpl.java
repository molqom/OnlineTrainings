package by.epam.web.dao.user;

import by.epam.web.dao.AbstractDao;
import by.epam.web.entity.User;
import by.epam.web.enums.Sql;
import by.epam.web.exception.DaoException;
import by.epam.web.mapper.UserRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {

    public UserDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException {

        return executeForSingleResult(
                Sql.FIND_BY_LOGIN_AND_PASSWORD.getQuery(),
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
    public void lockUser(long id) throws DaoException {
        execute(
                Sql.LOCK_USER.getQuery(),
                id);
    }

    @Override
    public void unlockUser(long id) throws DaoException {
        execute(
                Sql.UNLOCK_USER.getQuery(),
                id);
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


        execute(
                Sql.SAVE_USER.getQuery(),
                login,
                password,
                name,
                surname);
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public List<User> getAll() throws DaoException {
        return executeQuery(
                Sql.FIND_ALL_USERS.getQuery(),
                new UserRowMapper());
    }

    @Override
    protected String getTableName() {
        return Sql.TABLE_USERS.getQuery();
    }
}
