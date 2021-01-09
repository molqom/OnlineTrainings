package by.epam.web.service;

import by.epam.web.dao.DaoHelper;
import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.dao.user.UserDao;
import by.epam.web.entity.User;
import by.epam.web.exception.DaoException;
import by.epam.web.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private DaoHelperFactory daoHelperFactory;

    public AdminService(DaoHelperFactory daoHelperFactory) {
        this.daoHelperFactory = daoHelperFactory;
    }

    public List<User> createListOfUsers() throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            return dao.getAll();
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void lockUser(long id) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            dao.lockUser(id);
        } catch (SQLException | DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
    public void unlockUser(long id) throws ServiceException {
        try(DaoHelper daoHelper = daoHelperFactory.create()) {
            UserDao dao = daoHelper.createUserDao();
            dao.unlockUser(id);
        } catch (SQLException | DaoException e){
            throw new ServiceException(e.getMessage());
        }
    }
}
