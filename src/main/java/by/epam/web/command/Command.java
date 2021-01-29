package by.epam.web.command;

import by.epam.web.entity.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This interface using for executing some operation when we have request to servlet
 */
public interface Command {
    CommandResult execute(HttpServletRequest request, HttpServletResponse response);
}
