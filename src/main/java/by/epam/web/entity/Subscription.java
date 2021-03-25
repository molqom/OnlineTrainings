package by.epam.web.entity;

public class Subscription {
    private long id;
    private long userId;
    private String userName;
    private String userSurname;
    private long courseId;
    private String courseName;
    private String teacherName;
    private String teacherSurname;
    private int grade;
    private String feedback;

    public Subscription(
            long id,
            long userId,
            String userName,
            String userSurname,
            long courseId,
            String courseName,
            String teacherName,
            String teacherSurname,
            int grade,
            String feedback) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userSurname = userSurname;
        this.courseId = courseId;
        this.courseName = courseName;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
        this.grade = grade;
        this.feedback = feedback;
    }

    public Subscription(long id, long userId, long courseId, int grade, String feedback) {
        this.id = id;
        this.userId = userId;
        this.courseId = courseId;
        this.grade = grade;
        this.feedback = feedback;
    }

    public Subscription(long courseId, long userId) {
        this.courseId = courseId;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getCourseId() {
        return courseId;
    }

    public int getGrade() {
        return grade;
    }

    public String getFeedback() {
        return feedback;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }
}
