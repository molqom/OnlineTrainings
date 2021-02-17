package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.User;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static final String ID = "id";
    private final static String ROLE = "role";
    private static final String ACTIVE = "active";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String BAN = "ban";
    private final static String ERROR_MESSAGE_PARAMETER = "errorMessage";
    private static final String ERROR_MESSAGE = "Invalid creeds!";

    private final UserService service;

    public LoginCommand(UserService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);

        try {
            Optional<User> optUser = service.login(login, password);
            if (optUser.isPresent()) {
                User user = optUser.get();
                if (!user.isActive()) {
                    session.setAttribute(ACTIVE, BAN);
                    return CommandResult.redirect(BAN_CMD);
                }
                session.setAttribute(ID, user.getId());
                session.setAttribute(ROLE, user.getRole().toString());
                return CommandResult.redirect(MAIN_CMD);
            }
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);

        }
        if (login != null && password != null) {
            request.setAttribute(ERROR_MESSAGE_PARAMETER, ERROR_MESSAGE);
        }
        return CommandResult.forward(LOGIN_PAGE);
    }
}
