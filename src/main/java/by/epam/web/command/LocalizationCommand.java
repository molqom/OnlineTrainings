package by.epam.web.command;

import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LocalizationCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LocalizationCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String lang = request.getParameter("lang");
        session.setAttribute("lang", lang);
        String path = request.getRequestURI();
        request.setAttribute("path", path);
        LOGGER.info("localization command");
        LOGGER.info(path);
        if (path.equals("/WebApp/WEB-INF/view/news.jsp")){
            return "/WEB-INF/view/news.jsp";
        }

        return path.substring(7);
       // return path.split("/WebApp")[1];
    }
}
