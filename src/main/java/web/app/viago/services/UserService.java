package web.app.viago.services;

import web.app.viago.dao.users.UserDAO;
import web.app.viago.dao.users.UserDaoImpl;
import web.app.viago.model.User;

import java.util.List;

public class UserService {
    private final UserDAO userDao;

    public UserService() {
        this.userDao = new UserDaoImpl();
    }

    public void createUser(User user) {
        try {
            userDao.create(user);
        } catch (Exception e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
    }

    public User getUserById(int id) {
        try {
            return userDao.findById(id);
        } catch (Exception e) {
            System.out.println("Error retrieving user by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateUser(User user) {
        try {
            userDao.update(user);
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            userDao.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
    }

    public List<User> listUsersByRole(String role) {
        try {
            return userDao.findByRole(role);
        } catch (Exception e) {
            System.out.println("Error retrieving users by role: " + e.getMessage());
        }
        return null;
    }

    public List<User> getAllUsers() {
        try {
            List<User> users = userDao.getAllUsers();
            if (users != null) {
                return users;
            } else {
                System.out.println("No users found.");
            }

        } catch (Exception e) {
            System.out.println("Error retrieving all users: " + e.getMessage());
        }
        return null;
    }

}
