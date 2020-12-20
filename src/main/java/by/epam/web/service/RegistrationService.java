package by.epam.web.service;

import by.epam.web.dao.DaoHelper;
import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.dao.UserDao;
import by.epam.web.data.entity.User;
import by.epam.web.exception.DaoException;
import by.epam.web.exception.ServiceException;

import java.sql.SQLException;
import java.util.Optional;

public class RegistrationService {
    private DaoHelperFactory daoHelperFactory;

    public RegistrationService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }
    public void registration(String login,
                             String password,
                             String name,
                             String surname) throws ServiceException {
        User user = new User(login, password, name, surname);
        try(DaoHelper daoHelper = daoHelperFactory.create()){
            UserDao dao = daoHelper.createUserDao();
            dao.save(user);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
