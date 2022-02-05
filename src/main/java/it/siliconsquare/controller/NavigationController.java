package it.siliconsquare.controller;

import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.configuration.Configuration;
import it.siliconsquare.provider.Database;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@RestController
public class NavigationController {

    @GetMapping("/")
    public static void index(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/index.jsp");
        try {
            resp.setHeader("Accept-Language", "en-US,en;q=1.0");
            HttpSession session = req.getSession(true);
            if (session.getAttribute("configurator") == null) {
                session.setAttribute("configurator", new Configuration());
            }
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping("/login")
    public static void login(HttpServletRequest req, HttpServletResponse resp) {

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/login-register.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping("/suggestComponent")
    public static void suggestComponent(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/suggest-component.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping("/aboutUs")
    public static void aboutUs(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/about-us.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping("/templates")
    public static void templates(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/templates.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping("/account")
    public static void account(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/account.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping("/square")
    public static void square(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/square-page.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }
}
