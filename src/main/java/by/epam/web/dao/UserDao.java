package by.epam.web.dao;

import by.epam.web.data.entity.User;
import by.epam.web.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;
    boolean isUserExist(String userName);
}
