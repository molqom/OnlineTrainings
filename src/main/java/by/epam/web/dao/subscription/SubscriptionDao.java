package by.epam.web.dao.subscription;

import by.epam.web.dao.Dao;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.DaoException;

import java.util.List;

public interface SubscriptionDao extends Dao<Subscription> {
    List<Subscription> getSubscriptionsByUserId(long userId) throws DaoException;
    List<Subscription> getSubscriptionsByTeacherId(long userId) throws DaoException;
    void rate(long subscriptionId, int grade) throws DaoException;
    void addFeedback(long subscriptionId, String feedback) throws DaoException;

    boolean isSubscriptionExist(long courseId, long userId) throws DaoException;
}
