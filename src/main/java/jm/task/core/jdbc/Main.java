package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Андрей", "Андреев", (byte) 18);
        userService.saveUser("Иван", "Иванов", (byte) 15);
        userService.saveUser("Матвей", "Матвеев", (byte) 19);
        userService.saveUser("Сергей", "Сергеев", (byte) 16);

        System.out.println(userService.getAllUsers().toString());
        userService.createUsersTable();
        userService.dropUsersTable();
    }
}
