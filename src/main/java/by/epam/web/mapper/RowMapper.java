package by.epam.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    static final String USER_TABLE = "users";
    T map(ResultSet resultSet) throws SQLException;
    static RowMapper<?> create(String table) {
        switch (table) {
            case USER_TABLE:
                return new UserRowMapper();
            default:
                throw new IllegalArgumentException("Unknown table - " + table);
        }
    }
}
