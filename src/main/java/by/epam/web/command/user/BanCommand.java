package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BanCommand implements Command {
    private static final String BAN = "ban";
    private static final String ACTIVE = "active";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String active = (String) session.getAttribute(ACTIVE);
        if (active != null && active.equals(BAN)) {
            return CommandResult.forward(BAN_PAGE);
        } else {
            return CommandResult.redirect(LOGOUT_CMD);
        }
    }
}
