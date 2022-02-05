package it.siliconsquare;

import com.zaxxer.q2o.q2o;

import it.siliconsquare.common.HtmlVisualizer;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.common.redirect.ResourcePath;
import it.siliconsquare.connection.ftp.FtpManager;
import it.siliconsquare.controller.SitemapController;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.configuration.Configuration;
import it.siliconsquare.provider.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;

@SpringBootApplication
@RestController
public class SiliconSquareApplication {

    public static boolean DEBUG = false;

    static {
        Database.getInstance();
        Logger.getInstance();
    }

    {
        Properties properties = new Properties();
        URL applicationProperty = this.getClass().getClassLoader().getResource("application.properties");
        try {
            properties.load(new FileInputStream(applicationProperty.getPath()));
            DEBUG = Boolean.parseBoolean(properties.getProperty("debug"));
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }
    }

    public static void main(String[] args) throws SQLException {

        if (SiliconSquareApplication.DEBUG)
            System.out.println("[DEBUG MODE ENABLED]");
        SpringApplication.run(SiliconSquareApplication.class, args);

        var a = SpringApplication.getShutdownHandlers();
        a.add(new Runnable() {
            @Override
            public void run() {

                if (SiliconSquareApplication.DEBUG)
                    System.out.println("[DEBUG] Closing database connection");
                q2o.deinitialize();
            }
        });

    }

    @GetMapping("/test-filter/{type}")
    private static String testConfigFilter(@PathVariable String type, HttpServletRequest req,
            HttpServletResponse resp) {
        Configuration config = Database.getInstance().getConfigurationDAO().getConfigurationById(2);
        CompatibilityChecker c = new CompatibilityChecker(config);
        type = HtmlVisualizer.toEnum(type);

        System.out.println("TYPE - " + ComponentCategory.valueOf(type));

        ComponentCategory category = ComponentCategory.valueOf(type);
        return c.loadList(category);
    }

    @PostMapping("/upload")
    private static String ftp(@RequestParam("avatar") MultipartFile fileItem, HttpServletRequest req,
            HttpServletResponse resp) {

        String name = req.getParameter("name");
        FtpManager m = new FtpManager();
        m.uploadFile(ResourcePath.COMPONENTS_MEMORY, fileItem);
        try {
            return (name + " https://resources.siliconsquare.it" + ResourcePath.COMPONENTS_MEMORY.value
                    + fileItem.getOriginalFilename());
        } catch (Exception e) {
            Logger.getInstance().captureException(e);
        }
        return "";
    }

}