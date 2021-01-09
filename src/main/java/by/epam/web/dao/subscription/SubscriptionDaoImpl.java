package by.epam.web.dao.subscription;

import by.epam.web.dao.AbstractDao;
import by.epam.web.entity.Subscription;
import by.epam.web.enums.Sql;
import by.epam.web.exception.DaoException;
import by.epam.web.mapper.SubscriptionRowMapper;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class SubscriptionDaoImpl extends AbstractDao<Subscription> implements SubscriptionDao {
    private static final String TABLE_NAME = "subscriptions";
    private static final String ADD_SUBSCRIPTION =
            "insert into subscriptions (user_id, course_id) values (?, ?);";
    private static final String FIND_SUBSCRIPTIONS_BY_USER_ID =
            "select subscriptions.id, user_id, users.name, course_id, grade, feedback from subscriptions where user_id = ?;";
    public SubscriptionDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Subscription> getById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Subscription> getAll() throws DaoException {
        return null;
    }

    @Override
    public void save(Subscription item) throws DaoException {
        long userId = item.getUserId();
        long courseId = item.getCourseId();
        execute(ADD_SUBSCRIPTION, userId, courseId);
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public void removeById(long id) throws DaoException {
        execute(
                Sql.DELETE_SUBSCRIPTION.getQuery(),
                id);
    }

    @Override
    public List<Subscription> getSubscriptionsByUserId(long userId) throws DaoException {
        return executeQuery(
                Sql.FIND_SUBSCRIPTIONS_BY_USER_ID.getQuery(),
                new SubscriptionRowMapper(),
                userId);
    }

    @Override
    public List<Subscription> getSubscriptionsByTeacherId(long teacherId) throws DaoException {
        return executeQuery(
                Sql.FIND_SUBSCRIPTIONS_BY_TEACHER_ID.getQuery(),
                new SubscriptionRowMapper(),
                teacherId);
    }

    @Override
    public void rate(long subscriptionId, int grade) throws DaoException {
        execute(Sql.RATE.getQuery(), grade, subscriptionId);
    }

    @Override
    public void addFeedback(long subscriptionId, String feedback) throws DaoException {
        execute(Sql.ADD_FEEDBACK.getQuery(), feedback, subscriptionId);
    }

    @Override
    public boolean isSubscriptionExist(long courseId, long userId) throws DaoException {
        Optional<Subscription> subscription = executeForSingleResult(
                Sql.FIND_SUBSCRIPTION_BY_USER_ID_AND_COURSE_ID.getQuery(),
                new SubscriptionRowMapper(),
                userId,
                courseId
        );
        return subscription.isPresent();
    }
}
