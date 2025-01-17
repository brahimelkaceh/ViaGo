package web.app.viago.dao.users;

import web.app.viago.model.User;
import java.util.List;

public interface UserDAO {

    /**
     * Create a new user in the database.
     *
     * @param user the user to create
     */
    void create(User user);

    /**
     * Find a user by their ID.
     *
     * @param id the user ID
     * @return the user object, or null if not found
     */
    User findById(int id);

    /**
     * Find users by their role (e.g., USER, COMPANY).
     *
     * @param role the role of the user(s)
     * @return a list of users with the specified role
     */
    List<User> findByRole(String role);

    /**
     * Update an existing user in the database.
     *
     * @param user the user object with updated information
     */
    void update(User user);

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to delete
     */
    void delete(int id);

    /**
     * Get a list of all users.
     *
     * @return a list of all users
     */
    List<User> getAllUsers();
}
