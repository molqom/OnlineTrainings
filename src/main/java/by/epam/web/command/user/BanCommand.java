package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.constant.Parameter;
import by.epam.web.entity.CommandResult;
import by.epam.web.enums.Url;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BanCommand implements Command {
    private static final String BAN = "ban";
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String active = (String) session.getAttribute(Parameter.ACTIVE);
        if (active != null && active.equals(BAN)) {
            return CommandResult.forward(Url.BAN_PAGE);
        } else {
            return CommandResult.redirect(Url.LOGOUT_CMD);
        }
    }
}
