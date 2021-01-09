package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.command.teacher.FeedbackCommand;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.User;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("id")!=null) {
            return CommandResult.redirect(Url.MAIN_CMD);
        }
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            Optional<User> optUser = service.login(login, password);
            if (optUser.isPresent()) {
                User user = optUser.get();
                if (!user.isActive()){
                    session.setAttribute("active", "ban");
                    return CommandResult.redirect(Url.BAN_CMD);
                }
                session.setAttribute("id", user.getId());
                session.setAttribute("role", user.getRole().toString());
                return CommandResult.redirect(Url.MAIN_CMD);
            }
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);

        }
        if (login != null && password != null) {
            request.setAttribute("errorMessage", "Invalid creeds ");
        }
        return CommandResult.forward(Url.LOGIN_PAGE);
    }
}
