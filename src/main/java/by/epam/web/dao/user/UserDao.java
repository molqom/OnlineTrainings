package by.epam.web.dao.user;

import by.epam.web.dao.Dao;
import by.epam.web.entity.User;
import by.epam.web.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    boolean isUserExist(String userName);
    void lockUser(long id) throws DaoException;
    void unlockUser(long id) throws DaoException;
}
