package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.User;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersManageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UsersManageCommand.class);
    private final AdminService service;

    public UsersManageCommand(AdminService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> users = service.createListOfUsers();
            request.setAttribute(Parameter.USERS, users);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }

        return CommandResult.forward(Url.USERS_MANAGE_PAGE);
    }
}