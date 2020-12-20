package by.epam.web.connection;

import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "";

    public void connect() {
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("show tables");
            System.out.println(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
