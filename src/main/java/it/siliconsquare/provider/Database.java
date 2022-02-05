package it.siliconsquare.provider;

import com.google.gson.Gson;
import com.zaxxer.q2o.q2o;
import it.siliconsquare.SiliconSquareApplication;
import it.siliconsquare.common.HtmlVisualizer;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.connection.DatabaseConfig;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.Impl.administration.*;
import it.siliconsquare.model.DAO.Impl.component.ComponentCategoryDAOImpl;
import it.siliconsquare.model.DAO.Impl.configuration.ConfigurationDAOImpl;
import it.siliconsquare.model.DAO.administration.*;
import it.siliconsquare.model.DAO.component.ComponentCategoryDAO;
import it.siliconsquare.model.DAO.configuration.ConfigurationDAO;
import it.siliconsquare.model.administration.State;
import it.siliconsquare.model.component.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance = null;
    private static DataSource ds;
    Connection conn = null;

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    private Database() {
        try {
            ds = new DatabaseConfig().h2DataSource();
            q2o.initializeWithSpringTxSupport(ds);
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
    }

    public Connection getConnection() {

        try {
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        } catch (SQLException e) {
            Logger.getInstance().captureException(e);
        } finally {
            return conn;
        }
    }

    public ActionDAO getActionDAO() {
        return new ActionDAOImpl();
    }

    public BannedUserDAO getBannedUserDAO() {
        return new BannedUserDAOImpl();
    }

    public CommentDAO getCommentDAO() {
        return new CommentDAOImpl();
    }

    public LikeDAO getLikeDAO() {
        return new LikeDAOImpl();
    }

    public LogDAO getLogDAO() {
        return new LogDAOImpl();
    }

    public NotificationDAO getNotificationDAO() {
        return new NotificationDAOImpl();
    }

    public PermissionDAO getPermissionDAO() {
        return new PermissionDAOImpl();
    }

    public PostDAO getPostDAO() {
        return new PostDAOImpl();
    }

    public RoleDAO getRoleDAO() {
        return new RoleDAOImpl();
    }

    public ConfigurationDAO getConfigurationDAO() {
        return new ConfigurationDAOImpl();
    }

    public UserDAO getUserDAO() {
        return new UserDAOImpl();
    }

    public TopicIssuesDAO getTopicIssuesDAO() {
        return new TopicIssuesDAOImpl();
    }

    public ComponentCategoryDAO getComponentCategoryDAO() {
        return new ComponentCategoryDAOImpl();
    }

    public StateDAO getStateDAO() {
        return new StateDAOImpl();
    }

    public PollChoiceDAO getPollChoiceDAO() {
        return new PollChoiceDAOimpl();
    }

    public Component getComponentById(String type, int id) {
        type = HtmlVisualizer.toEnum(type);
        ComponentCategory category = ComponentCategory.valueOf(type);
        return (Component) DaoProvider.getFactory(category).getById(id);
    }

    public Component getPublishedComponentById(String type, int id) {
        type = HtmlVisualizer.toEnum(type);
        ComponentCategory category = ComponentCategory.valueOf(type);
        return (Component) DaoProvider.getFactory(category).getPublishedById(id);
    }

    public String getComponentCategoryFields(String type) {
        type = HtmlVisualizer.toEnum(type);
        ComponentCategory category = ComponentCategory.valueOf(type);

        if (SiliconSquareApplication.DEBUG)
            System.out.println("Category:" + category);

        var dao = DaoProvider.getFactory(category);

        if (dao == null)
            return "";
        
        return new Gson().toJson(dao.getFields().get(0));
    }

    public String getAllComponents(String type) {
        type = HtmlVisualizer.toEnum(type);
        ComponentCategory category = ComponentCategory.valueOf(type);

        if (SiliconSquareApplication.DEBUG)
            System.out.println("Category:" + category);

        var dao = DaoProvider.getFactory(category);

        if (dao == null)
            return "";

        return new Gson().toJson(dao.getAll());
    }

    public String getAllPublishedComponents(String type) {
        type = HtmlVisualizer.toEnum(type);
        ComponentCategory category = ComponentCategory.valueOf(type);

        if (SiliconSquareApplication.DEBUG)
            System.out.println("Category:" + category);

        var dao = DaoProvider.getFactory(category);

        if (dao == null)
            return "";

        return new Gson().toJson(DaoProvider.getFactory(category).getAllPublished());
    }

    public List<Component> getAllPublishedComponentsList(String type){
       type = HtmlVisualizer.toEnum(type);
        ComponentCategory category = ComponentCategory.valueOf(type);

        if (SiliconSquareApplication.DEBUG)
            System.out.println("Category:" + category);

        var dao = DaoProvider.getFactory(category);

        if (dao == null)
            return new ArrayList<>();
        return DaoProvider.getFactory(category).getAllPublished();
    }

    public List<Component> getAllUnpublishedComponents(String type) {
        type = HtmlVisualizer.toEnum(type);
        ComponentCategory category = ComponentCategory.valueOf(type);

        if (SiliconSquareApplication.DEBUG)
            System.out.println("Category:" + category);

        var dao = DaoProvider.getFactory(category);

        if (dao == null)
            return new ArrayList<>();
        return DaoProvider.getFactory(category).getAllUnpublished();
    }

    public boolean addNewComponent(String componentCategory, String myJson, int id) {
        componentCategory = HtmlVisualizer.toEnum(componentCategory);
        ComponentCategory category = ComponentCategory.valueOf(componentCategory);
        Component c = ComponentProvider.create(category, myJson);
        if (c == null)
            return false;
        c.setState(getStateDAO().getStateById(id));
        return DaoProvider.getFactory(category).save(c);
    }

    public boolean deleteComponent(String componentCategory, int id) {
        componentCategory = HtmlVisualizer.toEnum(componentCategory);
        ComponentCategory category = ComponentCategory.valueOf(componentCategory);
        return DaoProvider.getFactory(category).delete(id);
    }

    public boolean publishComponent(String componentCategory, int id) {
        componentCategory = HtmlVisualizer.toEnum(componentCategory);
        ComponentCategory category = ComponentCategory.valueOf(componentCategory);
        Component component = getComponentById(componentCategory, id);
        if (component == null)
            return false;
        return (DaoProvider.getFactory(category).setState(component, 1)) > 0;
    }

    public boolean editComponent(String componentCategory, String myJson, int idState, int id) {
        componentCategory = HtmlVisualizer.toEnum(componentCategory);
        ComponentCategory category = ComponentCategory.valueOf(componentCategory);

        Component c = ComponentProvider.create(category, myJson);
        if (c == null)
            return false;
        c.setId(id);
        State state = getStateDAO().getStateById(idState);
        c.setState(state);
        return DaoProvider.getFactory(category).update(c);

    }
}