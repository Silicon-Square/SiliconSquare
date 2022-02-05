package it.siliconsquare.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.siliconsquare.SiliconSquareApplication;
import it.siliconsquare.common.Security;
import it.siliconsquare.common.redirect.Action;
import it.siliconsquare.common.redirect.Page;
import it.siliconsquare.common.redirect.ResourcePath;
import it.siliconsquare.connection.ftp.FtpManager;
import it.siliconsquare.connection.response.StatusCode;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.administration.State;
import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.ComponentCategory;
import it.siliconsquare.provider.Database;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/component")
public class ComponentController {

    @RequestMapping(value = "")
    public static void allComponents(HttpServletRequest req, HttpServletResponse resp) {
        try {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/all-components.jsp");
            dispatcher.forward(req, resp);
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
    }

    @RequestMapping(value = "/{component}/{id}")
    public static void componentItem(@PathVariable String component, @PathVariable int id, HttpServletRequest req,
            HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession();
            Component c = null;
            if (Security.isAuthorized(Action.EDIT_COMPONENT, session))
                c = Database.getInstance().getComponentById(component, id);
            else
                c = Database.getInstance().getPublishedComponentById(component, id);

            if (SiliconSquareApplication.DEBUG)
                System.out.println("Component: " + component);

            if (c != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/component.jsp");
                req.setAttribute("component", c);
                dispatcher.forward(req, resp);
            } else
                resp.sendError(StatusCode.NOT_FOUND);

        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping(value = "/{componentCategory}.asp")
    public static String allComponents(@PathVariable String componentCategory, HttpServletRequest req,
            HttpServletResponse resp) {
        if (SiliconSquareApplication.DEBUG)
            System.out.println("CompCat: " + componentCategory);

        componentCategory = componentCategory.replace("#", "");

        HttpSession session = req.getSession();
        String componentsJson = "";
        if (Security.isAuthorized(Action.EDIT_COMPONENT, session))
            componentsJson = Database.getInstance().getAllComponents(componentCategory);
        else
            componentsJson = Database.getInstance().getAllPublishedComponents(componentCategory);

        if (SiliconSquareApplication.DEBUG)
            System.out.println(componentsJson);

        return componentsJson;
    }

    @GetMapping("/unpublishedComponents")
    public static List<Component> UnpublishedComponents(HttpServletRequest req, HttpServletResponse resp) {
        List<Component> unpublishedComponents = new ArrayList<>();
        for (String c : it.siliconsquare.common.redirect.ComponentCategory.ALL) {
            var componentC = Database.getInstance().getAllUnpublishedComponents(c);
            if (!componentC.isEmpty())
                unpublishedComponents.addAll(componentC);

        }

        if (SiliconSquareApplication.DEBUG)
        System.out.println(unpublishedComponents);

        return unpublishedComponents;
    }

    @RequestMapping(value = "/delete/{componentCategory}", method = RequestMethod.GET)
    public static boolean deleteComponent(@PathVariable String componentCategory,
            @RequestParam("newarray[]") List<String> ids, HttpServletRequest req,
            HttpServletResponse resp) {
        System.out.println("Prova");
        // print ids
        for (String id : ids) {
            System.out.println("In DELETE CONTROLLER" + id);
        }

        try {
            if (!Security.isAuthorized(Action.DELETE_COMPONENT, req.getSession())) {
                resp.sendError(StatusCode.UNAUTHORIZED);
                return false;
            }
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
        for (String id : ids)
            Database.getInstance().deleteComponent(componentCategory, Integer.parseInt(id));

        return true;
    }

    @RequestMapping(value = "/{component}")
    public static void componentList(@PathVariable String component, HttpServletRequest req, HttpServletResponse resp) {
        try {

            component = component.replace("#", "");
            System.out.println("componentC " + component);
            if (SiliconSquareApplication.DEBUG)
                System.out.println("Searching for component: " + component);

            String[] allComponentCategories = it.siliconsquare.common.redirect.ComponentCategory.ALL;
            String redirect = "";
            for (String c : allComponentCategories) {
                if (c.equalsIgnoreCase(component)) {
                    redirect = "";
                    break;
                }
                redirect = Page.ERROR;
            }
            if (!redirect.equals("")) {
                resp.sendError(StatusCode.NOT_FOUND);
                return;
            }
            String categoryDisplayName = Database.getInstance().getComponentCategoryDAO().getByPath("/" + component)
                    .getDisplayName();
            req.setAttribute("componentCategory", component);
            req.setAttribute("categoryDisplayName", categoryDisplayName);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/component-list.jsp");
            dispatcher.forward(req, resp);

        } catch (ServletException | IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    @GetMapping("/allComponentCategories")
    public static String getAllComponentCategoriesJson(HttpServletRequest req, HttpServletResponse resp) {
        List<ComponentCategory> componentCategoryList = Database.getInstance().getComponentCategoryDAO()
                .getAllComponentCategory();
        String json = new Gson().toJson(componentCategoryList);

        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);

        return json;
    }

    @GetMapping("/component-category/{componentCategory}")
    public static String getComponentCategoryJson(@PathVariable String componentCategory, HttpServletRequest req,
            HttpServletResponse resp) {

        System.out.println("ComponentCategory: " + componentCategory);
        String json = Database.getInstance().getComponentCategoryFields(componentCategory);

        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);

        return json;
    }

    @GetMapping("/component-category/{componentCategory}/{id}")
    public static String getSingleComponentCategoryJson(@PathVariable String componentCategory, @PathVariable int id,
            HttpServletRequest req,
            HttpServletResponse resp) {
        System.out.println("PROVAA");
        System.out.println("ComponentCategory: " + componentCategory);
        System.out.println("ID: " + id);

        String json = new Gson().toJson(Database.getInstance().getComponentById(componentCategory, id));

        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);

        return json;
    }

    @RequestMapping(value = "/add/{componentCategory}", method = RequestMethod.POST)
    public static boolean addNewComponent(@PathVariable String componentCategory,
            @RequestParam("component-image") MultipartFile image, HttpServletRequest req, HttpServletResponse resp) {

        if (!Security.isAuthorized(Action.ADD_COMPONENT, req.getSession())) {
                try {
					resp.sendError(StatusCode.UNAUTHORIZED);
				} catch (IOException e) {
                    Logger.getInstance().captureException(e);
				}
                return false;
            }

        JsonObject componentJson = new JsonObject();
        if (!image.equals("") && image != null) {
            FtpManager ftp = new FtpManager();
            String path = ftp.uploadFile(ResourcePath.COMPONENTS, image);
            if (path != null)
                componentJson.addProperty("image", path);
        }

        for (String attr : req.getParameterMap().keySet())
            componentJson.addProperty(attr, req.getParameter(attr));

        String myJson = new Gson().toJson(componentJson);
        if (SiliconSquareApplication.DEBUG)
            System.out.println(myJson);
        int idState = Integer.parseInt(req.getParameter("idState"));

        boolean result = Database.getInstance().addNewComponent(componentCategory, myJson, idState);
        return result;
    }

    @RequestMapping(value = "/edit/{componentCategory}/{id}", method = RequestMethod.POST)
    public static boolean editComponent(@PathVariable String componentCategory, @PathVariable int id,
            @RequestParam("component-image") MultipartFile image, HttpServletRequest req, HttpServletResponse resp) {
        JsonObject componentJson = new JsonObject();
        if (!image.equals("") && image != null) {
            FtpManager ftp = new FtpManager();
            String path = ftp.uploadFile(ResourcePath.COMPONENTS, image);
            if (path != null)
                componentJson.addProperty("image", path);
            else
                componentJson.addProperty("image", req.getParameter("image"));
        } else
            componentJson.addProperty("image", req.getParameter("image"));

        for (String attr : req.getParameterMap().keySet())
            componentJson.addProperty(attr, req.getParameter(attr));

        String myJson = new Gson().toJson(componentJson);
        if (SiliconSquareApplication.DEBUG)
            System.out.println(myJson);
        int idState = Integer.parseInt(req.getParameter("idState"));

        boolean result = Database.getInstance().editComponent(componentCategory, myJson, idState, id);
        return result;
    }

    @GetMapping("/allStates.asp")
    public static String getAllStatesJson(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (!Security.isAuthorized(Action.ALL_STATES, req.getSession())) {
                resp.sendError(StatusCode.UNAUTHORIZED);
                return "";
            }
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }

        List<State> stateList = Database.getInstance().getStateDAO().getAllStates();
        String json = new Gson().toJson(stateList);
        if (SiliconSquareApplication.DEBUG)
            System.out.println(json);
        return json;
    }

    @PostMapping("/publishComponent/{componentCategory}/{id}")
    public static boolean publishComponent(@PathVariable String componentCategory, @PathVariable int id,
            HttpServletRequest req, HttpServletResponse resp) {

        HttpSession session = req.getSession();
        if (Security.isAuthorized(Action.EDIT_COMPONENT, session))
            return (Database.getInstance().publishComponent(componentCategory, id));
        return false;

    }

}
