package by.epam.web.enums;

public enum Url {
    ERROR_400("/WEB-INF/view/error/400.jsp"),
    ERROR_500("/WEB-INF/view/error/500.jsp"),
    USERS_MANAGE_PAGE("/WEB-INF/view/usersManage.jsp"),
    USERS_MANAGE_CMD("/WebApp/controller?command=usersManage"),
    LOGIN_CMD("/WebApp/controller?command=login"),
    LOGIN_PAGE("/WEB-INF/view/login.jsp"),
    LOGOUT_CMD("/WebApp/controller?command=logout"),
    CONTACTS_PAGE("/WEB-INF/view/contacts.jsp"),
    INFO_PAGE("/WEB-INF/view/info.jsp"),
    MAIN_PAGE("/WEB-INF/view/main.jsp"),
    MAIN_CMD("/WebApp/controller?command=main"),
    BAN_PAGE("/WEB-INF/view/ban.jsp"),
    BAN_CMD("/WebApp/controller?command=ban"),
    NEWS_PAGE("/WEB-INF/view/news.jsp"),
    REGISTRATION_PAGE("/WEB-INF/view/registration.jsp"),
    TEACHERS_PAGE("/WEB-INF/view/teachers.jsp"),
    TEACHERS_CMD("/WebApp/controller?command=teachers"),
    TRAININGS_PAGE("/WEB-INF/view/trainings.jsp"),
    TRAININGS_CMD("/WebApp/controller?command=trainings"),
    ADD_COURSE_CMD("/WebApp/controller?command=addCourse"),
    DELETE_COURSE_CMD("/WebApp/controller?command=deleteCourse"),
    STUDENTS_CMD("/WebApp/controller?command=students"),
    STUDENTS_PAGE("/WEB-INF/view/students.jsp"),
    SUBSCRIBE_CMD("/WebApp/controller?command=subscribe"),
    SUBSCRIPTIONS_PAGE("/WEB-INF/view/subscriptions.jsp"),
    SUBSCRIPTIONS_CMD("/WebApp/controller?command=subscriptions");

    private final String url;
    Url(String url){
        this.url = url;
    }
    public String getUrl() {
        return url;
    }
}
