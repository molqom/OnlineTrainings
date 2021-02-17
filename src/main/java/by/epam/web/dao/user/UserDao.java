package by.epam.web.dao.user;

import by.epam.web.dao.Dao;
import by.epam.web.entity.User;
import by.epam.web.exception.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {
    String TABLE_USERS = "users";
    String FIND_USER_QUANTITY = "SELECT COUNT(*) as count FROM users";
    String FIND_BY_LOGIN_AND_PASSWORD = "SELECT users.id, users.login, users.password, users.name, users.surname, " +
            "roles.name, active FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE login = ? " +
            "AND password = sha1(?)";
    String SAVE_USER = "INSERT users(login, password, name, surname) VALUES(?, sha1(?), ?, ?)";
    String FIND_ALL_USERS = "select  users.id, login, password, users.name, surname, roles.name, active from users " +
            "left join roles on users.role_id = roles.id limit ? offset ?;";
    String LOCK_USER = "update users set active = false where id = ?";
    String UNLOCK_USER = "update users set active = true where id = ?";

    Optional<User> findUserByLoginAndPassword(String login, String password) throws DaoException;

    boolean isUserExist(String userName);

    void lockUser(long id) throws DaoException;

    void unlockUser(long id) throws DaoException;

    int getUsersQuantity() throws DaoException;
}
