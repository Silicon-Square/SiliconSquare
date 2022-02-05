package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.User;

import java.util.List;

public interface UserDAO {

    public List<User> getAllUser();

    User getUserById(int id);

    void saveUser(User c);

    boolean deleteUser(User c);

    boolean updateUser(User c);

    User authenticate(String user, String password);

    User register(String name, String surname, String username, String email, String password);

    User getUserByEmail(String email);

    boolean existsEmail(String email);

    boolean existsUsername(String username);

    User getUserByUsername(String username);


    boolean changePassword(String email, String password);

    boolean banUser(User u, int duration);

    List<User> getBannedUser();

    boolean unbanUser(User userByEmail);

    List<User> getUserByRole(String role);
}