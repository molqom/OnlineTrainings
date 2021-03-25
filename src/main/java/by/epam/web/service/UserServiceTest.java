package by.epam.web.service;

import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.entity.User;
import by.epam.web.exception.ServiceException;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class UserServiceTest {

    @Test
    public void login() throws ServiceException {
        UserService userService = new UserService(new DaoHelperFactory());
        Optional<User> user = userService.login("admin", "admin");
        User user1 = user.get();
        System.out.println(user1);
        System.out.println(user1.getRole().toString());
    }
}