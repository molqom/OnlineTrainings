package by.epam.web.service;

import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.exception.ServiceException;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegistrationServiceTest {

    @Test
    public void registration() throws ServiceException {
        String login = "login";
        String password = "password";
        String name = "name";
        String surname = "surname";
        RegistrationService service = new RegistrationService(new DaoHelperFactory());
        service.registration(login,password,name,surname);
    }
}