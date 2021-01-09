package by.epam.web.dao;

import by.epam.web.connection.ConnectionPool;
import by.epam.web.connection.ProxyConnection;
import by.epam.web.dao.course.CourseDao;
import by.epam.web.dao.course.CourseDaoImpl;
import by.epam.web.dao.subscription.SubscriptionDao;
import by.epam.web.dao.subscription.SubscriptionDaoImpl;
import by.epam.web.dao.user.UserDao;
import by.epam.web.dao.user.UserDaoImpl;
import by.epam.web.exception.DaoException;

import java.sql.SQLException;

public class DaoHelper implements AutoCloseable{
    private final ProxyConnection connection;
    public DaoHelper(ConnectionPool pool) throws DaoException {
        this.connection = pool.getConnection();
    }
    public UserDao createUserDao() {
        return new UserDaoImpl(connection);
    }
    public CourseDao createCourseDao() {
        return new CourseDaoImpl(connection);
    }
    public SubscriptionDao createSubscriptionDao(){
        return new SubscriptionDaoImpl(connection);
    }
    @Override
    public void close() throws SQLException {
        connection.close();
    }

    public void startTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
