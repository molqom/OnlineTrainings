package by.epam.web.dao.subscription;

import by.epam.web.dao.Dao;
import by.epam.web.entity.Subscription;
import by.epam.web.exception.DaoException;

import java.util.List;

public interface SubscriptionDao extends Dao<Subscription> {
    String TABLE_NAME = "subscriptions";
    String GET_ALL = "select * from ";
    String ADD_SUBSCRIPTION =
            "insert into subscriptions (user_id, course_id) values (?, ?);";
    String FIND_SUBSCRIPTIONS_BY_USER_ID =
            "select subscriptions.id, user_id, u.name as user_name, " +
                    "u.surname as user_surname, course_id, c.name as course_name, t.name as teacher_name, " +
                    "t.surname as teacher_surname, grade, feedback from subscriptions left join courses c " +
                    "on c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u " +
                    "on user_id = u.id where subscriptions.is_delete = false and user_id = ? limit ? offset ?;";
    String FIND_SUBSCRIPTIONS_BY_TEACHER_ID =
                    "select subscriptions.id, user_id, u.name as user_name, " +
                    "u.surname as user_surname, course_id, c.name as course_name, t.name as teacher_name, " +
                    "t.surname as teacher_surname, grade, feedback from subscriptions left join courses c on " +
                    "c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u on " +
                    "user_id = u.id where subscriptions.is_delete = false and t.id = ? limit ? offset ?;";
    String RATE = "update subscriptions set grade = ? where id = ?;";
    String ADD_FEEDBACK = "update subscriptions set feedback = ? where id = ?;";
    String FIND_SUBSCRIPTION_QUANTITY =
            "SELECT COUNT(*) as count FROM subscriptions where is_delete = false and user_id = ?;";
    String FIND_SUBSCRIPTION_QUANTITY_BY_TEACHER_ID =
                    "select COUNT(*) as count from subscriptions left join courses c " +
                    "on c.id = subscriptions.course_id where teacher_id = ? and subscriptions.is_delete=false;";
    String FIND_SUBSCRIPTION_BY_USER_ID_AND_COURSE_ID =
                    "select subscriptions.id, user_id, u.name as user_name, " +
                    "u.surname as user_surname, course_id, c.name as course_name, t.name as teacher_name, " +
                    "t.surname as teacher_surname, grade, feedback from subscriptions left join courses c on " +
                    "c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u on " +
                    "user_id = u.id where subscriptions.is_delete = false and c.is_delete = false and user_id = ? " +
                    "and course_id = ?;";
    String DELETE_SUBSCRIPTION = "update subscriptions set is_delete = true where id = ?";

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
