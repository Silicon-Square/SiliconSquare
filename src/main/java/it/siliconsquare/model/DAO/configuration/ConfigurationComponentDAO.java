package it.siliconsquare.model.DAO.configuration;

import it.siliconsquare.model.configuration.Configuration;

public interface ConfigurationComponentDAO<T> {

    T getById(Configuration config, int id);

    boolean save(Configuration config, T configurationComponent);

    boolean delete(Configuration config, int id);

    boolean update(Configuration config, T oldConfigurationComponent,T newConfigurationComponent);
    
}
