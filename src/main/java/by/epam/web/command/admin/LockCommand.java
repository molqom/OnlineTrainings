package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LockCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LockCommand.class);

    private final AdminService service;

    public LockCommand(AdminService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String stringUserId = request.getParameter(Parameter.LOCK);
        long userId = Long.parseLong(stringUserId);
        try {
            service.lockUser(userId);
            return CommandResult.redirect(Url.USERS_MANAGE_CMD);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(Url.ERROR_500);
        }
    }
}
