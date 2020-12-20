package by.epam.web.command;

import by.epam.web.dao.DaoHelperFactory;
import by.epam.web.data.entity.User;
import by.epam.web.exception.DaoException;
import by.epam.web.exception.MyRuntimeException;
import by.epam.web.exception.ServiceException;
import by.epam.web.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        DaoHelperFactory factory = new DaoHelperFactory();
        UserService service = new UserService(factory);
        boolean valid = false;
        try {
            Optional<User> user = service.login(login, password);
            valid = user.isPresent();
        } catch (ServiceException e) {
            throw new MyRuntimeException(e);
        }

        if (valid) {
            session.setAttribute("login", login);
            session.setAttribute("password", password);
            return "/WEB-INF/view/main.jsp";
        } else {
            request.setAttribute("errorMessage", "Invalid creeds " + login + " " + password);
            return "/login.jsp";
        }


    }
}
