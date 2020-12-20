package by.epam.web.data.repository;

import by.epam.web.data.entity.User;

public interface Repository {
    void add(User user);
    boolean isUsernameExist(String username);
    int size();
    User getUserByUsername(String username);
}
