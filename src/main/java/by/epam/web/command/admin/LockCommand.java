package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Role;
import by.epam.web.enums.Url;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LockCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LockCommand.class);

    private final AdminService service;

    public LockCommand(AdminService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (!session.getAttribute("role").equals(Role.ADMIN.toString())){
            return CommandResult.redirect(Url.LOGOUT_CMD);
        }
        String lockId = request.getParameter("lock");
        String unlockId = request.getParameter("unlock");
        if (lockId==null&&unlockId==null){
            return CommandResult.redirect(Url.LOGOUT_CMD);
        }
        if (lockId!=null){
            long id = Long.parseLong(lockId);
            try {
                service.lockUser(id);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                return CommandResult.forward(Url.ERROR_500);
            }
        }
        else {
            long id = Long.parseLong(unlockId);
            try {
                service.unlockUser(id);
            } catch (ServiceException e) {
                LOGGER.info(e.getMessage(), e);
                return CommandResult.forward(Url.ERROR_500);
            }
        }
        return CommandResult.redirect(Url.ADMIN_CMD);
    }
}
