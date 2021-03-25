package by.epam.web.dao;

import by.epam.web.connection.ConnectionPool;
import by.epam.web.exception.DaoException;

public class DaoHelperFactory {
    public static DaoHelper create() throws DaoException {
        return new DaoHelper(ConnectionPool.getInstance());
    }
}
