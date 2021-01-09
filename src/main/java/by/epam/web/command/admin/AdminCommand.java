package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Role;
import by.epam.web.entity.User;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class AdminCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminCommand.class);
    private final AdminService service;

    public AdminCommand(AdminService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!session.getAttribute("role").equals(Role.ADMIN.toString())) {
            return CommandResult.redirect(Url.LOGOUT_CMD);
        }
        try {
            List<User> users = service.createListOfUsers();
            request.setAttribute("users", users);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }

        return CommandResult.forward(Url.ADMIN_PAGE);
    }
}