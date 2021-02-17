package by.epam.web.controller;

import by.epam.web.command.Command;
import by.epam.web.command.CommandFactory;
import by.epam.web.connection.ConnectionPool;
import by.epam.web.entity.CommandResult;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(Servlet.class);
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
            //logg
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

    @Override
    public void destroy() {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            connectionPool.destroy();
        } catch (Exception e) {
            LOGGER.info(e.getMessage(), e);
        }
        super.destroy();
    }
}
