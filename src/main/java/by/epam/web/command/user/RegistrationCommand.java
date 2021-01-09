package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Url;
import by.epam.web.exception.CredentialValidException;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.RegistrationService;
import by.epam.web.validator.RegistrationValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(RegistrationCommand.class);

    private final RegistrationService service;

    public RegistrationCommand(RegistrationService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        boolean valid = false;
        //if username!valid error mess
        //if pass!valid error mess
        try {
            RegistrationValidator validator = new RegistrationValidator();
            validator.valid(login, password, repeatPassword);
        } catch (CredentialValidException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return CommandResult.redirect(Url.REGISTRATION_PAGE);
        }
        try {
            service.registration(login, password, name, surname);
            valid = true;
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            e.printStackTrace();
        }
        if (valid) {
            request.setAttribute("successMessage", "Registration is success! Now you can log in");
            return CommandResult.redirect(Url.LOGIN_CMD);
        } else {
            request.setAttribute("errorMessage", "User with this username already exist");
            return CommandResult.redirect(Url.REGISTRATION_PAGE);
        }
    }
}
