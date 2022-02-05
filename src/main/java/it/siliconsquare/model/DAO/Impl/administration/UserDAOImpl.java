package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.common.Security;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.administration.UserDAO;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.provider.Database;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    @Autowired
    private List<User> userList;
    private String sql = "SELECT * FROM public.user , public.role  WHERE public.user.id_role  = public.role.id_role ";

    public UserDAOImpl() {
        userList = new ArrayList<>();
    }

    public List<User> getAllUser() {
        return Q2ObjList.fromSelect(User.class, sql + ";");
    }

    @Override
    public User getUserById(int id) {
        return Q2Obj.fromSelect(User.class, sql + "and id_user = ?;", id);
    }

    @Override
    public void saveUser(User u) {
        Q2Obj.insert(u);
    }

    @Override
    public boolean deleteUser(User u) {
        String query = "DELETE FROM public.user WHERE id_user=?;";
        int affectedRows = Q2Sql.executeUpdate(query, u.getIdUser());

        String logMessage = "Deleted user [Email] - " + u.getEmail();
        if (affectedRows == 0)
            logMessage = "Attempt to delete user [Email] + " + u.getEmail();

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    @Override
    public boolean updateUser(User u) {
        String query = "UPDATE public.user SET Name =?,  Surname=? ,  Username=? ,  Email=? ,  id_role=?, avatar=? WHERE id_user=?;";
        String name = u.getName();
        String surname = u.getSurname();
        String username = u.getUsername();
        String email = u.getEmail();
        int idRole = u.getIdRole();
        String avatar = u.getAvatar();
        int idUser = u.getIdUser();
        int affectedRows = Q2Sql.executeUpdate(query, name, surname, username, email, idRole, avatar, idUser);

        String logMessage = "Updated user [Email] - " + email;

        if (affectedRows == 0)
            logMessage = "Attempt Registration [Name, Surname, Username, Email, new ID Role, avatar] - " + name + ","
                    + surname + "," + "," + username + "," + email + "," + idRole + "," + avatar;

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    /**
     * @param user     username / email of a user
     * @param password
     * @return User - The user data, if user exists <br>
     *         null - If user doesn't exists
     */
    @Override
    public User authenticate(String user, String password) {
        String logMessage = "Authentication [Email] - " + user;
        User existsUser = null;
        if (this.existsEmail(user)) {
            String userPassword = null;

            String query = "SELECT public.user.password_hash FROM public.user WHERE public.user.email=? ";
            try {
                Connection con = Database.getInstance().getConnection();
                PreparedStatement p = con.prepareStatement(query);
                p.setString(1, user);
                ResultSet rs = p.executeQuery();
                if (rs.next())
                    userPassword = rs.getString(1);

                if (userPassword != null && Security.confrontPasswords(password, userPassword))
                    existsUser = getUserByEmail(user);
                con.close();
            } catch (Exception e) {
                Logger.getInstance().captureException(e);
            }

        } else
            logMessage = "Attempt Authentication [Email] - " + user;

        Logger.getInstance().captureMessage(logMessage);

        return existsUser;
    }

    /**
     * Allows to register a new user into the database.
     *
     * @param name
     * @param surname
     * @param username
     * @param email
     * @param password Plain password (NOT encrypted)
     * @return User - If registration is succesfull. <br>
     *         null - If user already exists with the specified {@code email}
     */
    @Override
    public User register(String name, String surname, String username, String email, String password) {
        String query = "INSERT INTO public.user ( Name ,  Surname ,  Username ,  Email ,  Password_Hash ) VALUES(?,?,?,?,?);";
        String encryptedPassword = Security.hashPassword(password);

        int affectedRows = Q2Sql.executeUpdate(query, name, surname, username, email, encryptedPassword);

        String logMessage = "Registration [Email] - " + email;

        if (affectedRows == 0)
            logMessage = "Attempt Registration [Name, Surname, Username, Email] - " + name + "," + surname + "," + ","
                    + username + "," + email;

        Logger.getInstance().captureMessage(logMessage);

        if (affectedRows > 0)
            return this.getUserByEmail(email);
        return null;
    }

    /**
     * @param email
     * @return User - If user exists<br>
     *         null - If user NOT exists with the specified {@code email}
     */
    @Override
    public User getUserByEmail(String email) {
        return Q2Obj.fromSelect(User.class,
                sql + "and public.user.email = ?;",
                email);

    }

    /**
     * Checks if a user with a specific email exists
     *
     * @param email
     * @return true - exists <br>
     *         false - NOT exists
     */
    @Override
    public boolean existsEmail(String email) {
        return getUserByEmail(email) != null;
    }

    /**
     * Checks if a user with a specific username exists
     *
     * @param username
     * @return true - exists <br>
     *         false - NOT exists
     */
    @Override
    public boolean existsUsername(String username) {
        return getUserByUsername(username) != null;
    }

    /**
     * @param username
     * @return User - If user exists<br>
     *         null - If user NOT exists with the specified {@code username}
     */
    @Override
    public User getUserByUsername(String username) {
        return Q2Obj.fromSelect(User.class, sql + "and username = ?;", username);
    }

    /**
     * @param email    User with this email
     * @param password The new password
     * @return true - password successfully changed <br>
     *         false - user doesn't exist with the specified {@code email}
     */
    @Override
    public boolean changePassword(String email, String password) {
        String query = "UPDATE public.user SET password_hash = ? WHERE email = ?";
        String encryptedPassword = Security.hashPassword(password);

        int affectedRows = Q2Sql.executeUpdate(query, encryptedPassword, email);

        String logMessage = "Changed Password for [Email] - " + email;

        if (affectedRows <= 0)
            logMessage = "Attempted Change Password for not existing [Email] - " + email;

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    @Override
    public boolean banUser(User u, int duration) {

        String query = "INSERT INTO public.banned_user (id_user, duration) VALUES(?,?);";
        int idUser = u.getIdUser();
        String email = u.getEmail();
        int affectedRows = Q2Sql.executeUpdate(query, idUser, duration);

        String logMessage = "Banned user [Email] - " + email;

        if (affectedRows == 0)
            logMessage = "Attempt banning User [Email] - " + email;

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    @Override
    public List<User> getBannedUser() {
        List<User> banned = new ArrayList<>();

        String query = "SELECT public.banned_user.id_user FROM public.banned_user";
        PreparedStatement p = null;
        try {
            Connection con = Database.getInstance().getConnection();
            p = con.prepareStatement(query);

            ResultSet rs = p.executeQuery();
            System.out.println(rs.getFetchSize());
            while (rs.next())
                banned.add(this.getUserById(rs.getInt(1)));
        } catch (SQLException e) {
            Logger.getInstance().captureException(e);
        }
        System.out.println("Banned user: " + banned);
        return banned;
    }

    @Override
    public boolean unbanUser(User u) {
        String query = "DELETE FROM public.banned_user WHERE id_user = ?;";
        int idUser = u.getIdUser();
        int affectedRows = Q2Sql.executeUpdate(query, idUser);

        String email = u.getEmail();
        String logMessage = "Unbanned user [Email] - " + email;

        if (affectedRows == 0)
            logMessage = "Attempt unbanning User [Email] - " + email;

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    @Override
    public List<User> getUserByRole(String role) {
        return Q2ObjList.fromSelect(User.class, sql + "and role_name = ?;", role);
    }

}