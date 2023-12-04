package com.sams.service;

import com.sams.dao.UserDao;
import com.sams.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public void saveUser(User user) throws SQLException {
        userDao.saveUser(user);
    }

    public boolean checkUser(String username, String password) throws SQLException {
        return userDao.checkUser(username, password);
    }
}
