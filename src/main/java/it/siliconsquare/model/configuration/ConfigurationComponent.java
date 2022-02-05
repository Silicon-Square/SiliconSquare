package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;

public interface ConfigurationComponent<T>  {

    int getIdConfiguration();

    /**
     * @return the id of the configuration component which is the PK into the database table
     */
    int getIdConfigurationComponent(); 

    T getComponent();

    void setIdConfiguration(int idConfiguration);

    void setIdConfigurationComponent(int idConfigurationComponent);

    void setComponent(Component component);

}
