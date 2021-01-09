package by.epam.web.controller;

import by.epam.web.command.Command;
import by.epam.web.command.CommandFactory;
import by.epam.web.entity.CommandResult;

import javax.servlet.RequestDispatcher;
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
            CommandResult commandResult = command.execute(req, resp);
            dispatch(commandResult, req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    private void dispatch(CommandResult commandResult, HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String url = commandResult.getUrl();
        boolean isRedirect = commandResult.isRedirect();
        if (isRedirect){
            resp.sendRedirect(url);
        } else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(url);
            dispatcher.forward(req,resp);
        }
    }
}
