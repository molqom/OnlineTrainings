package by.epam.web;

import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.exception.DaoException;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.TrainingsService;
import org.apache.log4j.Logger;

public class MainTest {
    public void mainOld(String[] args) {
        //mysql --user=root -p
        //without password
        String url = "jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String pass = "";


    }
    private static final int LIMIT_COURSES_ON_PAGE = 5;
    private static final Logger LOGGER = Logger.getLogger(MainTest.class);
    public static void main(String[] args) throws ServiceException, DaoException {
        DaoHelperFactory factory = new DaoHelperFactory();
        TrainingsService service = new TrainingsService(factory);

        service.findCoursesByTeacherId(3);

    }

}
