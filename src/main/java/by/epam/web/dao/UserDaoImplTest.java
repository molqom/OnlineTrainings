package by.epam.web.dao;

import by.epam.web.dao.user.UserDao;
import by.epam.web.exception.DaoException;
import org.junit.Test;

public class UserDaoImplTest {

    @Test
    public void findUserByLoginAndPassword() throws DaoException {
        UserDao userDao = DaoHelperFactory.create().createUserDao();
        userDao.lockUser(3);
        System.out.println("end");
    }
}