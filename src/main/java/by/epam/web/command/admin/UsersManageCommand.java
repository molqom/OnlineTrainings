package by.epam.web.command.admin;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.entity.User;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.AdminService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UsersManageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(UsersManageCommand.class);
    private static final String USERS = "users";
    private static final String NUM_OF_PAGE = "numOfPage";
    private static final String PAGES_QUANTITY = "pagesQuantity";
    private static final int USERS_QUANTITY_ON_PAGE = 2;
    private static final int DEFAULT_NUM_OF_PAGE = 1;
    private final AdminService service;

    public UsersManageCommand(AdminService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String numOfPageParam = request.getParameter(NUM_OF_PAGE);
        int numOfPage = DEFAULT_NUM_OF_PAGE;
        if (numOfPageParam!=null){
            numOfPage = Integer.parseInt(numOfPageParam);
        }
        request.setAttribute(NUM_OF_PAGE, numOfPage);
        try {
            int pagesQuantity = service.calculatePagesQuantity(USERS_QUANTITY_ON_PAGE);
            request.setAttribute(PAGES_QUANTITY, pagesQuantity);
            List<User> users = service.createListOfUsers(numOfPage, USERS_QUANTITY_ON_PAGE);
            request.setAttribute(USERS, users);
            return CommandResult.forward(USERS_MANAGE_PAGE);
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            return CommandResult.forward(ERROR_500);
        }
    }
}