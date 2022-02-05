package it.siliconsquare.controller;

import it.siliconsquare.SiliconSquareApplication;
import it.siliconsquare.common.PasswordGenerator;
import it.siliconsquare.common.redirect.Page;
import it.siliconsquare.connection.mail.EmailSender;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.model.configuration.Configuration;
import it.siliconsquare.provider.Database;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class LoginController {

    @GetMapping("/logout")
    public void logout(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            if (SiliconSquareApplication.DEBUG)
                System.out.println(session.getAttributeNames());
            if (session.getAttribute("user") != null)
                session.invalidate();
            resp.sendRedirect(Page.HOME);
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @PostMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("user") != null)
            return "Error,You are already logged";

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HttpSession session = req.getSession(true);
        User res = Database.getInstance().getUserDAO().authenticate(email, password);

        if (res != null) {
            session.setAttribute("user", res);
            req.setAttribute("user", res);
            return "Success," + Page.HOME;
        }

        return "Error, wrong email or password";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("user") != null)
            return "Error,You are already logged";

        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        boolean existsEmail = Database.getInstance().getUserDAO().existsEmail(email);
        boolean existsUsername = Database.getInstance().getUserDAO().existsUsername(username);

        if (existsEmail)
            return "Error,Email already used";

        if (existsUsername)
            return "Error,Username already used";

        User user = Database.getInstance().getUserDAO().register(name, surname, username, email, password);
        if (user != null)
            return this.login(req, resp);

        return "Error,Error during register";
    }

    @PostMapping("/lost-password")
    public void sendNewPassword(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getSession().getAttribute("user") != null)
            return;
        try {
            String email = req.getParameter("email");
            String redirect = "/password-changed";
            String randomPassword = PasswordGenerator.generatePassword(8);

            boolean exists = Database.getInstance().getUserDAO().changePassword(email, randomPassword);

            if (exists) {
                EmailSender.sendSimpleMessage(email, "Password Changed", randomPassword);
                System.out.println("Password changed!");
            } else
                redirect = Page.ERROR;

            resp.sendRedirect(redirect);

        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

}
