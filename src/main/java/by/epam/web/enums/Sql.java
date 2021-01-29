package by.epam.web.enums;

public enum Sql {

    //queries for subscription dao
    FIND_SUBSCRIPTIONS_BY_USER_ID("select subscriptions.id, user_id, u.name as user_name, " +
            "u.surname as user_surname, course_id, c.name as course_name, t.name as teacher_name, " +
            "t.surname as teacher_surname, grade, feedback from subscriptions left join courses c " +
            "on c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u " +
            "on user_id = u.id where subscriptions.is_delete = false and user_id = ? limit ? offset ?;"),
    FIND_SUBSCRIPTIONS_BY_TEACHER_ID("select subscriptions.id, user_id, u.name as user_name, " +
            "u.surname as user_surname, course_id, c.name as course_name, t.name as teacher_name, " +
            "t.surname as teacher_surname, grade, feedback from subscriptions left join courses c on " +
            "c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u on " +
            "user_id = u.id where subscriptions.is_delete = false and t.id = ? limit ? offset ?;"),
    RATE("update subscriptions set grade = ? where id = ?;"),
    ADD_FEEDBACK("update subscriptions set feedback = ? where id = ?;"),
    FIND_SUBSCRIPTION_QUANTITY("SELECT COUNT(*) as count FROM subscriptions where is_delete = false " +
            "and user_id = ?;"),
    FIND_SUBSCRIPTION_QUANTITY_BY_TEACHER_ID("select COUNT(*) as count from subscriptions left join courses c " +
            "on c.id = subscriptions.course_id where teacher_id = ? and subscriptions.is_delete=false;"),
    FIND_SUBSCRIPTION_BY_USER_ID_AND_COURSE_ID("select subscriptions.id, user_id, u.name as user_name, " +
            "u.surname as user_surname, course_id, c.name as course_name, t.name as teacher_name, " +
            "t.surname as teacher_surname, grade, feedback from subscriptions left join courses c on " +
            "c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u on " +
            "user_id = u.id where subscriptions.is_delete = false and c.is_delete = false and user_id = ? " +
            "and course_id = ?;"),
    DELETE_SUBSCRIPTION("update subscriptions set is_delete = true where id = ?"),

    //queries for course dao
    TABLE_COURSES("courses"),
    GET_COURSE_BY_ID("select id, name, teacher_id from courses where id = ?"),
    FIND_COURSE_QUANTITY("SELECT COUNT(*) as count FROM courses where is_delete = false;"),
    FIND_COURSES_ON_CURRENT_PAGE("select courses.id, courses.name, users.name, users.surname from courses " +
            "left join users on courses.teacher_id = users.id where courses.is_delete = false limit ? offset ?;"),
    FIND_COURSES_FOR_CURRENT_TEACHER("select courses.id, courses.name, users.name from courses left join users " +
            "on courses.teacher_id = users.id where courses.teacher_id = ? and courses.is_delete = false;"),
    ADD_COURSE("insert courses (name, teacher_id) values (?, ?);"),
    DELETE_COURSE("update courses c left join subscriptions s on c.id = s.course_id set s.is_delete = true, " +
            "c.is_delete = true where c.id = ?;"),

    //queries for user dao
    TABLE_USERS("users"),
    FIND_USER_QUANTITY("SELECT COUNT(*) as count FROM users"),
    FIND_BY_LOGIN_AND_PASSWORD("SELECT users.id, users.login, users.password, users.name, users.surname, " +
            "roles.name, active FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE login = ? " +
            "AND password = sha1(?)"),
    SAVE_USER("INSERT users(login, password, name, surname) VALUES(?, sha1(?), ?, ?)"),
    FIND_ALL_USERS("select  users.id, login, password, users.name, surname, roles.name, active from users " +
            "left join roles on users.role_id = roles.id limit ? offset ?;"),
    LOCK_USER("update users set active = false where id = ?"),
    UNLOCK_USER("update users set active = true where id = ?");

    private final String query;
    Sql(String query){
        this.query = query;
    }
    public String getQuery() {
        return query;
    }
}
