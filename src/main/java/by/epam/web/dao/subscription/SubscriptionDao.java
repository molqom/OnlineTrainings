package by.epam.web.dao.subscription;

import by.epam.web.dao.Dao;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.DaoException;

import java.util.List;

public interface SubscriptionDao extends Dao<Subscription> {
    List<Subscription> getSubscriptionsByUserId(
            long userId,
            int numOfPage,
            int subscriptionQuantityOnPage) throws DaoException;

    List<Subscription> getSubscriptionsByTeacherId(long userId, int numOfPage, int studentsQuantityOnPage)
            throws DaoException;

    void rate(long subscriptionId, int grade) throws DaoException;

    void addFeedback(long subscriptionId, String feedback) throws DaoException;

    boolean isSubscriptionExist(long courseId, long userId) throws DaoException;

    int subscriptionQuantity(long userId) throws DaoException;

    int subscriptionQuantityByTeacherId(long teacherId) throws DaoException;
}
