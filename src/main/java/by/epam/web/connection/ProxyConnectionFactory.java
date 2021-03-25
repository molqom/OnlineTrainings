package by.epam.web.connection;

import by.epam.web.exception.ConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProxyConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private final ConnectionPool connectionPool;

    public ProxyConnectionFactory(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    public ProxyConnection create() throws ConnectionException {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            return new ProxyConnection(connection, connectionPool);
        } catch (SQLException e) {
            throw new ConnectionException(e.getMessage(), e);
        }
    }
}
