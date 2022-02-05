package it.siliconsquare.common;

import it.siliconsquare.SiliconSquareApplication;
import it.siliconsquare.common.redirect.Action;
import it.siliconsquare.common.redirect.Page;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.provider.Database;

import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpSession;

public class Security {

    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    /**
     * Confront if a plain password mathches a String rapresenting the encrypted
     * password
     *
     * @param plainPassword    NOT encrypted password
     * @param dbHashedPassword encrypted password
     * @return true - passowrds matching <br>
     *         false - passwords NOT matching
     */
    public static boolean confrontPasswords(String plainPassword, String dbHashedPassword) {
        return BCrypt.checkpw(plainPassword, dbHashedPassword);
    }

    /**
     * Check if the user is authorized to do an action / operation
     *
     * @param page    {@link Page}
     * @param session The session of user who wants to do an action / operation
     * @return
     */
    public static boolean isAuthorized(String page, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return isAuthorized(page, user, false);
    }

    /**
     * Check if the user is authorized to do an action / operation
     *
     * @param action  {@link Action}
     * @param session The session of user who wants to do an action / operation
     * @return
     */
    public static boolean isAuthorized(Action action, HttpSession session) {
        User user = (User) session.getAttribute("user");
        return isAuthorized(action.toString(), user, true);
    }

    /**
     * Check if the user is authorized to do an action / operation
     *
     * @param page {@link Page}
     * @param user Who wants to do an action / operation
     * @return Who wants to do an action / operation
     */
    public static boolean isAuthorized(String page, User user) {
        return isAuthorized(page, user, false);
    }

    /**
     * Check if the user is authorized to do an action / operation
     *
     * @param action {@link Action}
     * @param user   Who wants to do an action / operation
     * @return Who wants to do an action / operation
     */
    public static boolean isAuthorized(Action action, User user) {
        return isAuthorized(action.toString(), user, true);
    }

    /**
     * Check if the user is authorized to do an action / operation
     *
     * @param request     The string value of the request/action/operation: <br>
     *                    For example from {@link Action} or {@link Page}
     * @param user        Who wants to do an action / operation
     * @param actionBased Choose based on what you want to check the permission <br>
     *                    false - {@link Page} <br>
     *                    true - {@link Action}
     * @return
     */
    private static boolean isAuthorized(String request, User user, boolean actionBased) {

        if (SiliconSquareApplication.DEBUG)
            System.out.println("Security.isAuthorized()" + request + " " + user + " " + actionBased);
        if (SiliconSquareApplication.DEBUG)
            return true;

        if (user == null || user.isBanned()) {
            if (!actionBased) {
                switch (request) {
                    case Page.ABOUTUS:
                        return true;
                    case Page.HOME:
                        return true;
                    case Page.COMPONENT:
                        return true;
                    default:
                        return false;
                }
            }
            return false;
        }

        String roleName = user.getRole().getRoleName();
        boolean controller = false;
        boolean genius = false;

        if (roleName.equalsIgnoreCase("Admin") || roleName.equalsIgnoreCase("Manager"))
            controller = true;

        if (roleName.equalsIgnoreCase("Genius"))
            genius = true;

        if (actionBased) {
            Action action = Action.valueOf(request);
            switch (action) {
                case ALL_USERS:
                case SBAN_USERS:
                case BAN_USERS:
                case EDIT_USER:
                case UPDATE_USER:
                case EDIT_COMPONENT:
                case ADMIN_PANEL:
                case DELETE_COMPONENT:
                case DELETE_USERS:
                case ALL_STATES:
                case EDIT_ROLE:
                    return controller;
                case SUGGEST_COMPONENT:
                    return genius;
                case ADD_COMPONENT:
                    return controller || genius;
                default:
                    return false;
            }
        } else {
            switch (request) {
                case Page.ABOUTUS:
                    return true;
                case Page.HOME:
                    return true;
                case Page.PROFILE:
                    return true;
                case Page.SOCIAL:
                    return true;
                case Page.USERS:
                    return controller;
                case Page.COMPONENT:
                    return true;
                case Page.SUGGESTCOMPONENT:
                    return controller;
                default:
                    return false;
            }
        }
    }


    /**
     * 
     * @return if master has a higher role than the slave <br> <br>
     * true - master has a higher role than the slave <br> <br>
     * false - master has a lower role than the slave
     */
    public static boolean upperToUser(User master, User slave){
        return master.getIdRole()<slave.getIdRole();   //e.g. Admin is 1, Manager is 2, Genius is 3 etc.
    }

    /**
     * 
     * @return if master has a higher role than the slave <br> <br>
     * true - master has a higher role than the slave <br> <br>
     * false - master has a lower role than the slave
     */
    public static boolean upperToUser(int masterId, String slaveIdString){
        int slaveId = Integer.parseInt(slaveIdString);
        User master = Database.getInstance().getUserDAO().getUserById(masterId);
        User slave = Database.getInstance().getUserDAO().getUserById(slaveId);
        System.out.println("master: "+master.getRole().getRoleName()+" slave: "+slave.getRole().getRoleName());
        System.out.println("RESULT:" +(master.getIdRole()<slave.getIdRole()));
        return master.getIdRole()<slave.getIdRole();   //e.g. Admin is 1, Manager is 2, Genius is 3 etc.
    }

}