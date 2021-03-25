package by.epam.web.entity;

public class Course {
    private long id;
    private String name;
    private String teacherName;
    private String teacherSurname;
    private long teacherId;

    public Course(long id, String name, String teacherName, String teacherSurname) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.teacherSurname = teacherSurname;
    }

    public Course(String name, long teacherId) {
        this.name = name;
        this.teacherId = teacherId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public String getTeacherSurname() {
        return teacherSurname;
    }

    public void setTeacherSurname(String teacherSurname) {
        this.teacherSurname = teacherSurname;
    }
}
