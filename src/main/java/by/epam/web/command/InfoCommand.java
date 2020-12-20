package by.epam.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InfoCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        if (session.getAttribute("login")!=null) {
            return "/WEB-INF/view/info.jsp";
        }
        else {
            return "/WEB-INF/view/login.jsp";
        }
    }
}
