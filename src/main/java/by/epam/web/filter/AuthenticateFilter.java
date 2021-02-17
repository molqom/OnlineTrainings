package by.epam.web.filter;

import by.epam.web.exception.IllegalCommandException;
import by.epam.web.validator.AccessValidator;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthenticateFilter implements Filter {
    private final static String LOGIN_COMMAND = "/controller?command=login";
    private final static String COMMAND = "command";
    private final static String ROLE = "role";
    private final static String ERROR_MESSAGE_PARAMETER = "errorMessage";
    private final static String ERROR_MESSAGE = "You not have access!";
    private final static Logger LOGGER = Logger.getLogger(AuthenticateFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute(ROLE);
        String command = request.getParameter(COMMAND);
        AccessValidator validator = new AccessValidator(role);

        try {
            boolean isAccess = validator.isValid(command);
            if (!isAccess) {
                session.invalidate();
                request.setAttribute(ERROR_MESSAGE_PARAMETER, ERROR_MESSAGE);
                request.getRequestDispatcher(LOGIN_COMMAND).forward(request, servletResponse);
            } else {
                filterChain.doFilter(request, servletResponse);
            }
        } catch (IllegalCommandException e) {
            LOGGER.info(e.getMessage(), e);
            session.invalidate();
            request.setAttribute(ERROR_MESSAGE_PARAMETER, ERROR_MESSAGE);
            request.getRequestDispatcher(LOGIN_COMMAND).forward(request, servletResponse);
        }
    }

    @Override
    public void destroy() {
    }
}
