package by.epam.web.controller;

import by.epam.web.command.Command;
import by.epam.web.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            String commandParam = req.getParameter("command");
            Command command = CommandFactory.create(commandParam);
            String page = command.execute(req, resp);
            dispatch(page, req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(final String page, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher(page).forward(req, resp);
    }
}
