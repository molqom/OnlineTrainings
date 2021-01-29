package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
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
    private static final String ERROR_MESSAGE = "User with this username already exist";
    private static final String SUCCESS_MESSAGE = "Registration is success! Now you can log in";

    private final RegistrationService service;

    public RegistrationCommand(RegistrationService service) {
        this.service = service;
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter(Parameter.LOGIN);
        String password = request.getParameter(Parameter.PASSWORD);
        String repeatPassword = request.getParameter(Parameter.REPEAT_PASSWORD);
        String name = request.getParameter(Parameter.NAME);
        String surname = request.getParameter(Parameter.SURNAME);

        boolean valid = false;
        try {
            RegistrationValidator validator = new RegistrationValidator();
            validator.valid(login, password, repeatPassword);
        } catch (CredentialValidException e) {
            request.setAttribute(Parameter.ERROR_MESSAGE, e.getMessage());
            return CommandResult.forward(Url.REGISTRATION_PAGE);
        }
        try {
            service.registration(login, password, name, surname);
            valid = true;
        } catch (ServiceException e) {
            LOGGER.info(e.getMessage(), e);
            e.printStackTrace();
        }
        if (valid) {
            request.setAttribute(Parameter.SUCCESS_MESSAGE, SUCCESS_MESSAGE);
            return CommandResult.redirect(Url.LOGIN_CMD);
        } else {
            request.setAttribute(Parameter.ERROR_MESSAGE, ERROR_MESSAGE);
            return CommandResult.redirect(Url.REGISTRATION_PAGE);
        }
    }
}
