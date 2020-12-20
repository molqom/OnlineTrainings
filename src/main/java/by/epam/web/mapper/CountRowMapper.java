package by.epam.web.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CountRowMapper implements RowMapper {

    @Override
    public Integer map(ResultSet resultSet) throws SQLException {
        return resultSet.getInt("count");
    }
}
