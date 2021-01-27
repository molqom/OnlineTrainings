package by.epam.web.command.teacher;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.Course;
import by.epam.web.enums.Url;
import by.epam.web.exception.DaoException;
import by.epam.web.service.TrainingsService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class TeachersCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(TeachersCommand.class);

    private final TrainingsService service;

    public TeachersCommand(TrainingsService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        long id = (long) session.getAttribute(Parameter.ID);
        try {
            List<Course> courses = service.findCoursesByTeacherId(id);
            request.setAttribute(Parameter.COURSES, courses);
            return CommandResult.forward(Url.TEACHERS_PAGE);
        } catch (DaoException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
