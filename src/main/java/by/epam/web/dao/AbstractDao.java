package by.epam.web.dao;

import by.epam.web.exception.DaoException;
import by.epam.web.mapper.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T> implements Dao<T> {
    private final Connection connection;

    protected AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, RowMapper<T> mapper, Object... params) throws DaoException {

        try (PreparedStatement preparedStatement = createStatement(query, params)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = mapper.map(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
    protected boolean execute(String query, Object... params) throws DaoException {
        try (PreparedStatement preparedStatement = createStatement(query, params)) {
            return preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private PreparedStatement createStatement(String query, Object... params) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        for (int i = 1; i < params.length + 1; i++) {
            statement.setObject(i, params[i - 1]);
        }
        return statement;
    }

    @Override
    public List<T> getAll() throws DaoException {
        String table = getTableName();
        RowMapper<T> mapper = (RowMapper<T>) RowMapper.create(table);
        return executeQuery("SELECT * FROM " + table, mapper);
    }

    @Override
    public void save(T item) throws DaoException {
        String query = "INSERT users(user_name, user_pswd) VALUES(?, sha1(?))";
        String login;
        Object[] params = new Object[]{"asd", "adas"};
        boolean isExecute = false;
    }

    protected Optional<T> executeForSingleResult(
            String query,
            RowMapper<T> builder,
            Object... params
    ) throws DaoException {
        List<T> items = executeQuery(query, builder, params);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else if (items.size() > 1) {
            throw new IllegalArgumentException("More than one record found");
        } else {
            return Optional.empty();
        }
    }

    protected abstract String getTableName();
}
