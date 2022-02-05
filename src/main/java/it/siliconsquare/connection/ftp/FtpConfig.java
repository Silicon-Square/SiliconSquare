package it.siliconsquare.connection.ftp;

import it.siliconsquare.logger.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

class FtpConfig {

    private String server;
    private int port;
    private String username;
    private String password;

    public FtpConfig() {
        this.initialize();
    }

    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    //    @Bean(name = "ftpSource")
    public void initialize() {
        Properties properties = new Properties();
        URL applicationProperty = this.getClass().getClassLoader().getResource("application.properties");
        try {
            properties.load(new FileInputStream(applicationProperty.getPath()));
        } catch (IOException e) {
            Logger.getInstance().captureException(e);
        }

        server = properties.getProperty("ftp.server");
        username = properties.getProperty("ftp.username");
        password = properties.getProperty("ftp.password");
        port = Integer.valueOf(properties.getProperty("ftp.port"));
    }

}

