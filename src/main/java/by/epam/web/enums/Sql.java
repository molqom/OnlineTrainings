package by.epam.web.enums;

public enum Sql {

    //queries for subscription dao
    FIND_SUBSCRIPTIONS_BY_USER_ID("select subscriptions.id, user_id, u.name as user_name, u.surname as user_surname, course_id, c.name    as course_name, t.name    as teacher_name, t.surname as teacher_surname, grade, feedback from subscriptions left join courses c on c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u on user_id = u.id where user_id = ?;"),
    FIND_SUBSCRIPTIONS_BY_TEACHER_ID("select subscriptions.id, user_id, u.name as user_name, u.surname as user_surname, course_id, c.name    as course_name, t.name    as teacher_name, t.surname as teacher_surname, grade, feedback from subscriptions left join courses c on c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u on user_id = u.id where t.id = ?;"),
    RATE("update subscriptions set grade = ? where id = ?;"),
    ADD_FEEDBACK("update subscriptions set feedback = ? where id = ?;"),
    FIND_SUBSCRIPTION_BY_USER_ID_AND_COURSE_ID("select subscriptions.id, user_id, u.name as user_name, u.surname as user_surname, course_id, c.name    as course_name, t.name    as teacher_name, t.surname as teacher_surname, grade, feedback from subscriptions left join courses c on c.id = subscriptions.course_id left join users t on c.teacher_id = t.id left join users u on user_id = u.id where user_id = ? and course_id = ?;"),
    DELETE_SUBSCRIPTION("delete from subscriptions where id = ?"),

    //queries for course dao
    TABLE_COURSES("courses"),
    FIND_COURSE_QUANTITY("SELECT COUNT(*) as count FROM courses;"),
    FIND_COURSES_ON_CURRENT_PAGE("select courses.id, courses.name, users.name from courses left join users on courses.teacher_id = users.id limit ? offset ?;"),
    FIND_COURSES_FOR_CURRENT_TEACHER("select courses.id, courses.name, users.name from courses left join users on courses.teacher_id = users.id where teacher_id = ?;"),
    ADD_COURSE("insert courses (name, teacher_id) values (?, ?);"),
    DELETE_COURSE("delete from courses where id = ?"),

    //queries for user dao
    TABLE_USERS("users"),
    FIND_BY_LOGIN_AND_PASSWORD("SELECT users.id, users.login, users.password, users.name, users.surname, roles.name, active FROM users LEFT JOIN roles ON users.role_id = roles.id WHERE login = ? AND password = sha1(?)"),
    SAVE_USER("INSERT users(login, password, name, surname) VALUES(?, sha1(?), ?, ?)"),
    FIND_ALL_USERS("select  users.id, login, password, users.name, surname, roles.name, active from users left join roles on users.role_id = roles.id;"),
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
