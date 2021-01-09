package by.epam.web.service;

import by.epam.web.dao.DaoHelper;
import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.dao.subscription.SubscriptionDao;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.DaoException;
import by.epam.web.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionService {
    private DaoHelperFactory factory;

    public SubscriptionService(DaoHelperFactory factory) {
        this.factory = factory;
    }

    public boolean subscribe(long courseId, long userId) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            Subscription subscription = new Subscription(courseId, userId);
            if (!dao.isSubscriptionExist(courseId, userId)) {
                dao.save(subscription);
                return true;
            }
            return false;
        } catch (DaoException | SQLException e) {
            //logg
            throw new ServiceException(e.getMessage());
        }
    }
    public List<Subscription> findSubscriptions(long userId) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            return dao.getSubscriptionsByUserId(userId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public List<Subscription> findStudents(long teacherId) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            return dao.getSubscriptionsByTeacherId(teacherId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void rate(long subscriptionId, int grade) throws ServiceException{
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            dao.rate(subscriptionId, grade);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public void addFeedback(long subscriptionId, String feedback) throws ServiceException{
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            dao.addFeedback(subscriptionId, feedback);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public void unsubscribe(long subscriptionId) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            dao.removeById(subscriptionId);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
}
