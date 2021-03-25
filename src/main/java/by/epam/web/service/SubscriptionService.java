package by.epam.web.service;

import by.epam.web.dao.DaoHelper;
import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.dao.course.CourseDao;
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
                dao.add(subscription);
                return true;
            }
            return false;
        } catch (DaoException | SQLException e) {
            //logg
            throw new ServiceException(e.getMessage());
        }
    }
    public List<Subscription> findSubscriptions(long userId, int numOfPage, int subscriptionQuantityOnPage)
            throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            return dao.getSubscriptionsByUserId(userId, numOfPage, subscriptionQuantityOnPage);
        } catch (DaoException | SQLException e) {
            throw new ServiceException(e.getMessage());
        }
    }
    public List<Subscription> findStudents(long teacherId, int numOfPage, int studentsQuantityOnPage)
            throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            return dao.getSubscriptionsByTeacherId(teacherId, numOfPage, studentsQuantityOnPage);
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
    public int calculatePagesQuantity(int subscriptionQuantityOnPage, long userId) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            int subscriptionQuantity= dao.subscriptionQuantity(userId);
            int pagesQuantity = (int)Math.ceil((double)subscriptionQuantity / subscriptionQuantityOnPage);
            return pagesQuantity;
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    public int calculatePagesOfStudentQuantity(long teacherId, int studentsQuantityOnPage) throws ServiceException {
        try (DaoHelper daoHelper = factory.create()) {
            SubscriptionDao dao = daoHelper.createSubscriptionDao();
            int studentsQuantity= dao.subscriptionQuantityByTeacherId(teacherId);
            int pagesQuantity = (int)Math.ceil((double)studentsQuantity/ studentsQuantityOnPage);
            return pagesQuantity;
        } catch (SQLException | DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
