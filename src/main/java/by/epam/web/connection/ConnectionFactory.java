package by.epam.web.connection;

import by.epam.web.exception.DaoException;

import java.sql.*;

public class ConnectionFactory {
    private static final String url = "jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    public static ProxyConnection create() throws DaoException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(url, user, password);
            return new ProxyConnection(connection, ConnectionPool.getInstance());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
}
