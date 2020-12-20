package by.epam.web.command;

import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.exception.CredentialValidException;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.RegistrationService;
import by.epam.web.validator.RegistrationValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeat-password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");

        DaoHelperFactory factory = new DaoHelperFactory();
        RegistrationService service = new RegistrationService(factory);
        boolean valid = false;
        //if username!valid error mess
        //if pass!valid error mess
        try {
            RegistrationValidator validator = new RegistrationValidator();
            validator.valid(login, password, repeatPassword);
        } catch (CredentialValidException e) {
            request.setAttribute("errorMessage", e.getMessage());
            return "/registration.jsp";
        }
        try {
            service.registration(login, password, name, surname);
            valid = true;
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        if (valid) {
            request.setAttribute("successMessage", "Registration is success! Now you can log in");
            return "/login.jsp";
        } else {
            request.setAttribute("errorMessage", "User with this username already exist");
            return "/registration.jsp";
        }
    }
}
