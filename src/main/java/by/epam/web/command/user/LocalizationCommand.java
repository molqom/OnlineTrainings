package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LocalizationCommand implements Command {
    private static final String LANG = "lang";
    private static final String REFERER = "Referer";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String lang = request.getParameter(LANG);
        session.setAttribute(LANG, lang);
        String page = request.getHeader(REFERER);
        if (page == null) {
            page = request.getContextPath();
            return CommandResult.redirect(page);
        }
        return CommandResult.redirect(page);
    }
}
