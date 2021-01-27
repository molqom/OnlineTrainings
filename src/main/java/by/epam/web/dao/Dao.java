package by.epam.web.dao;

import by.epam.web.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> getById(long id) throws DaoException;

    List<T> getAll() throws DaoException;

    void add(T item) throws DaoException;

    void removeById(long id) throws DaoException;
}
