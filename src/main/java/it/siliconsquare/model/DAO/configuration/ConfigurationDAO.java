package it.siliconsquare.model.DAO.configuration;

import it.siliconsquare.model.administration.User;
import it.siliconsquare.model.configuration.Configuration;

import java.util.List;

public interface ConfigurationDAO {
    public List<Configuration> getAllConfiguration();

    Configuration getConfigurationById(int id);

    Configuration getConfigurationByUser(User u);

    int saveConfiguration(Configuration configuration);

    boolean deleteConfiguration(Configuration configuration);

    int updateConfiguration(Configuration configuration);

    List<Configuration> getAllConfigurationsByUser(User user);
}
