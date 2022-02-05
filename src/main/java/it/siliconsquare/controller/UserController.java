package it.siliconsquare.controller;

import com.google.gson.Gson;
import it.siliconsquare.SiliconSquareApplication;
import it.siliconsquare.common.PasswordGenerator;
import it.siliconsquare.common.Security;
import it.siliconsquare.common.redirect.Page;
import it.siliconsquare.common.redirect.ResourcePath;
import it.siliconsquare.connection.ftp.FtpManager;
import it.siliconsquare.connection.mail.EmailSender;
import it.siliconsquare.connection.response.StatusCode;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.administration.Action;
import it.siliconsquare.model.administration.Role;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.model.configuration.Configuration;
import it.siliconsquare.provider.Database;

import org.springframework.util.concurrent.SuccessCallback;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserController {

    @GetMapping("/users")
    public static void getAllUsers(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!Security.isAuthorized(Page.USERS, req.getSession())) {
                resp.sendError(StatusCode.UNAUTHORIZED);
                return;
            }
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/users.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @RequestMapping(value = "/send-new-password/{id}", method = RequestMethod.POST)
    public boolean sendNewPasswordAdmin(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null)
            return false;

        if (Security.isAuthorized(it.siliconsquare.common.redirect.Action.EDIT_USER, user) || id == user.getIdUser()) {
            String randomPassword = PasswordGenerator.generatePassword(8);
            String email = Database.getInstance().getUserDAO().getUserById(id).getEmail();

            boolean exists = Database.getInstance().getUserDAO().changePassword(email, randomPassword);

            if (exists) {
                EmailSender.sendSimpleMessage(email, "Password Changed", randomPassword);
                return true;
            }
        }
        return false;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public static boolean delete(@RequestParam("newarray[]") List<String> emails, HttpServletRequest req,
            HttpServletResponse resp) {
        if (!Security.isAuthorized(it.siliconsquare.common.redirect.Action.DELETE_USERS, req.getSession()))
            return false;
        for (String email : emails) {
            User user = Database.getInstance().getUserDAO().getUserByEmail(email);
            if (!Security.upperToUser((User) req.getSession().getAttribute("user"), user))
                continue;
            Logger.getInstance().captureMessage(
                    "deleting user: emails = [" + emails + "], req = [" + req + "], resp = [" + resp + "]");
            Database.getInstance().getUserDAO().deleteUser(user);
        }
        return true;
    }

    @RequestMapping(value = "/ban", method = RequestMethod.GET)
    public static String ban(@RequestParam("newarray[]") List<String> emails, @RequestParam("days") String daysBan,
            HttpServletRequest req, HttpServletResponse resp) {

        String response = "OK";

        if (!Security.isAuthorized(it.siliconsquare.common.redirect.Action.DELETE_USERS, req.getSession()))
            return "You are not authorized to perform this action";
        for (String email : emails) {
            User user = Database.getInstance().getUserDAO().getUserByEmail(email);
            User master = (User) req.getSession().getAttribute("user");
            if (master.getEmail().equals(user.getEmail()) || !Security.upperToUser(master, user)) {
                response = "You are not authorized to perform this action to some users!";
                continue;
            }
            Logger.getInstance().captureMessage("Banning user: emails = [" + emails + "], daysBan = [" + daysBan
                    + "], req = [" + req + "], resp = [" + resp + "]");
            Database.getInstance().getUserDAO().banUser(user, Integer.parseInt(daysBan));

        }

        return new Gson().toJson(response);
    }

    @RequestMapping(value = "/sbanuser", method = RequestMethod.GET)
    private static String unbanUsers(@RequestParam("newarray[]") List<String> emails, HttpServletRequest req,
            HttpServletResponse resp) {

        String response = "OK";

        if (!Security.isAuthorized(it.siliconsquare.common.redirect.Action.DELETE_USERS, req.getSession()))
            return "You are not authorized to perform this action";
        for (String email : emails) {
            User user = Database.getInstance().getUserDAO().getUserByEmail(email);
            User master = (User) req.getSession().getAttribute("user");
            if (master.getEmail().equals(user.getEmail()) || !Security.upperToUser(master, user)) {
                response = "You are not authorized to perform this action to some users!";
                continue;
            }
            Logger.getInstance().captureMessage(
                    "Unbanning user: emails = [" + emails + "], req = [" + req + "], resp = [" + resp + "]");
            Database.getInstance().getUserDAO().unbanUser(user);
        }
        return new Gson().toJson(response);
    }

    @GetMapping("/allUsers")
    public static String getAllUsersJson(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!Security.isAuthorized(Page.USERS, req.getSession())) {
                resp.sendError(StatusCode.UNAUTHORIZED);
                resp.sendRedirect(Page.HOME + Page.ERROR);
                return "";
            }
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
        List<User> userList = Database.getInstance().getUserDAO().getAllUser();
        String json = new Gson().toJson(userList);
        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);
        return json;
    }

    @GetMapping("/allRoles")
    public static String getAllRolesJson(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!Security.isAuthorized(Page.USERS, req.getSession())) {
                resp.sendError(StatusCode.UNAUTHORIZED);
                resp.sendRedirect(Page.HOME + Page.ERROR);
                return "";
            }
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
        List<Role> roleList = Database.getInstance().getRoleDAO().getAllRole();

        User master = (User) req.getSession().getAttribute("user");

        List<Role> roleListForMaster = new ArrayList<>();
        for(Role r : roleList)
            if(r.getIdRole() > master.getIdRole()) 
                roleListForMaster.add(r);
        

        String json = new Gson().toJson(roleListForMaster);
        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);
        return json;
    }

    /**
     * @return All data related to the user with the specified id
     */
    @GetMapping("/edit/{id}")
    public static String editUser(@PathVariable int id, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (id != ((User) req.getSession().getAttribute("user")).getIdUser()
                    && !Security.isAuthorized(it.siliconsquare.common.redirect.Action.EDIT_USER, req.getSession())) {
                resp.sendError(StatusCode.UNAUTHORIZED);
                resp.sendRedirect(Page.HOME + Page.ERROR);
                return "";
            }
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
        User editUser = Database.getInstance().getUserDAO().getUserById(id);
        String json = new Gson().toJson(editUser);
        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);
        return json;
    }

    @GetMapping("/allActions")
    public static String getAllActionsJson(HttpServletRequest req, HttpServletResponse resp) {
        List<Action> actionList = Database.getInstance().getActionDAO().getAllAction();
        String json = new Gson().toJson(actionList);
        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);
        return json;
    }

    public static List<Action> getAllActions() {
        List<Action> actionList = Database.getInstance().getActionDAO().getAllAction();
        return actionList;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public static boolean updateUser(@PathVariable int id, @RequestParam("avatar") MultipartFile avatar,
            HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (id != ((User) req.getSession().getAttribute("user")).getIdUser() &&
                    !Security.isAuthorized(it.siliconsquare.common.redirect.Action.UPDATE_USER, req.getSession())) {
                System.out.println("Unauthorized");
                resp.sendError(StatusCode.UNAUTHORIZED);
                return false;
            }
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }

        var roleId = Integer.parseInt(req.getParameter("idRole"));
        String email = req.getParameter("email");
        User u = Database.getInstance().getUserDAO().getUserById(id);
        if (u != null) {
            u.setName(req.getParameter("name"));
            u.setSurname(req.getParameter("surname"));
            u.setEmail(email);
            u.setUsername(req.getParameter("username"));
            u.setRole(Database.getInstance().getRoleDAO().getRoleById(roleId));
            if (!avatar.equals("") && avatar != null) {
                FtpManager ftp = new FtpManager();
                String path = ftp.uploadFile(ResourcePath.USER_AVATAR, avatar);
                if (path != null)
                    u.setAvatar(path);
            }
            if (!Database.getInstance().getUserDAO().updateUser(u))
                Logger.getInstance()
                        .captureMessage("Errore durante l'aggiornamento dell'utente [ID: " + id + "] " + email + "!");

            if (id == ((User) req.getSession().getAttribute("user")).getIdUser()) {
                Configuration config = (Configuration) req.getSession().getAttribute("configurator");
                req.getSession().invalidate();
                req.getSession().setAttribute("user", u);
                req.getSession().setAttribute("configurator", config);
            }
        }
        return true;
    }

    @RequestMapping(value = "/getUserByRole/{id_role}", method = RequestMethod.POST)
    public static String editUser(@PathVariable String id_role, HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!Security.isAuthorized(Page.USERS, req.getSession())) {
                resp.sendError(StatusCode.UNAUTHORIZED);
                resp.sendRedirect(Page.HOME + Page.ERROR);
                return "";
            }
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
        List<User> admins = Database.getInstance().getUserDAO().getUserByRole(id_role);
        String jsonAdmins = new Gson().toJson(admins);
        return jsonAdmins;
    }
}
