package by.epam.web.command.user;

import by.epam.web.command.Command;
import by.epam.web.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return CommandResult.redirect(LOGIN_CMD);
    }
}
