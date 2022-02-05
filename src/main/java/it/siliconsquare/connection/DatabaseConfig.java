package it.siliconsquare.connection;

import com.zaxxer.hikari.HikariDataSource;
import it.siliconsquare.logger.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

@Configuration
public class DatabaseConfig {

    @Bean(name = "dataSource")
    public DataSource h2DataSource() {
        Properties properties = new Properties();
        URL applicationProperty = this.getClass().getClassLoader().getResource("application.properties");
        try {
            properties.load(new FileInputStream(applicationProperty.getPath()));
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        String driver = properties.getProperty("spring.datasource.driver-class-name");
        String url = properties.getProperty("spring.datasource.url");
        long maxLifeTime = Long.parseLong(properties.getProperty("spring.datasource.hikari.maxLifetime"));
        long idleTimeout = Long.parseLong(properties.getProperty("spring.datasource.hikari.idleTimeout"));
        int maxPoolSize = Integer.parseInt(properties.getProperty("spring.datasource.hikari.maximumPoolSize"));
        int minIdle = Integer.parseInt(properties.getProperty("spring.datasource.hikari.minimumIdle"));

        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setMaxLifetime(maxLifeTime);
        dataSource.setMaximumPoolSize(maxPoolSize);
        dataSource.setIdleTimeout(idleTimeout);
        dataSource.setMinimumIdle(minIdle);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driver);
        dataSource.setJdbcUrl(url);
        return dataSource;
    }

}