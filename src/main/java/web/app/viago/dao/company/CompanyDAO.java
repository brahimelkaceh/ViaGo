package web.app.viago.dao.company;

import web.app.viago.model.Company;


import java.util.List;

public interface CompanyDAO {

    /**
     * Create a new user in the database.
     *
     * @param company the company to create
     */
    boolean create(Company company);


    /**
     * Authenticate a user by their email and password.
     *
     * @param email    the user's email
     * @param password the user's password
     * @return the user object if authentication is successful, null if not
     */
    Company login(String email, String password);

    /**
     * Find a user by their ID.
     *
     * @param id the user ID
     * @return the company object, or null if not found
     */
    Company findById(int id);

    /**
     * Find a user by their ID.
     *
     * @param email the company email
     * @return the company object, or null if not found
     */
    Company findByEmail(String email);

    /**
     * Update an existing user in the database.
     *
     * @param company the company object with updated information
     */
    void update(Company company);

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
    List<Company> getAllCompanies();


}
