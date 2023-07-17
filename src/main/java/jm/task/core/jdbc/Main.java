package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

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
