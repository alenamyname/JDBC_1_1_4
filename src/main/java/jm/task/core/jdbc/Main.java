package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();

        User[] users ={
                new User("Андрей", "Андреев", (byte) 18),
                new User("Иван", "Иванов", (byte) 15),
                new User("Матвей", "Матвеев", (byte) 19),
                new User("Сергей", "Сергеев", (byte) 16)
        };

        for (User user : users) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
        }

        System.out.println(userService.getAllUsers());
        userService.createUsersTable();
        userService.dropUsersTable();
    }
}
