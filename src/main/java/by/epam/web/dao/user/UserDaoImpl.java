package by.epam.web.dao.user;

import by.epam.web.dao.AbstractDao;
import by.epam.web.entity.User;
import by.epam.web.exception.DaoException;
import by.epam.web.mapper.CountRowMapper;
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
    public void lockUser(long id) throws DaoException {
        execute(
                LOCK_USER,
                id);
    }

    @Override
    public void unlockUser(long id) throws DaoException {
        execute(
                UNLOCK_USER,
                id);
    }

    @Override
    public Optional<User> getById(long id) {
        return Optional.empty();
    }

    @Override
    public void add(User item) throws DaoException {
        String login = item.getLogin();
        String password = item.getPassword();
        String name = item.getName();
        String surname = item.getSurname();


        execute(
                SAVE_USER,
                login,
                password,
                name,
                surname);
    }

    @Override
    public void removeById(long id) {

    }

    @Override
    public List<User> getAll(int numOfPage, int usersQuantityOnPage) throws DaoException {
        int numFirstUserOnPage = (numOfPage - 1) * usersQuantityOnPage;
        return executeQuery(
                FIND_ALL_USERS,
                new UserRowMapper(),
                usersQuantityOnPage,
                numFirstUserOnPage);
    }

    @Override
    public int getUsersQuantity() throws DaoException {
        List<Integer> count = executeQuery(
                FIND_USER_QUANTITY,
                new CountRowMapper());
        return count.get(0);
    }

    @Override
    protected String getTableName() {
        return TABLE_USERS;
    }
}
