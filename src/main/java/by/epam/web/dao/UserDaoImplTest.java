package by.epam.web.dao;

import by.epam.web.data.entity.User;
import by.epam.web.exception.DaoException;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserDaoImplTest {

    @Test
    public void findUserByLoginAndPassword() throws DaoException {
        UserDao userDao = DaoHelperFactory.create().createUserDao();
        Optional<User> user = userDao.findUserByLoginAndPassword("admin", "admin");
        System.out.println(user.get().getLogin());
        System.out.println("end");
    }
}