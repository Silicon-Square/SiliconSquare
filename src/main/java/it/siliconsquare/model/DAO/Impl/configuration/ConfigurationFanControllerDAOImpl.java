package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.FanController;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationFanControllerDAOImpl implements ConfigurationComponentDAO<FanController>{

	@Override
    public FanController getById(Configuration config, int id) {
        String selectQuery = "SELECT e.* FROM fan_controller e, configuration c, configuration_fan_controller conf_ext WHERE c.id_configuration = conf_ext.id_configuration AND conf_ext.id_fan_controller = e.id_fan_controller AND c.id_configuration = ? AND e.id_fan_controller = ?";
        FanController c = Q2Obj.fromSelect(FanController.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, FanController configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_fan_controller (id_configuration, id_fan_controller) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save fan controller with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved FanController [" + idComponent + "]  into configuration [" + config.getIdConfiguration()
                    + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_fan_controller WHERE id_configuration = ? AND id_fan_controller = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete fan controller with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted FanController [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, FanController oldComponent, FanController newComponent) {
        String updateQuery = "UPDATE configuration_fan_controller SET id_fan_controller = ? WHERE id_configuration = ? AND id_fan_controller = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(), config.getIdConfiguration(),
                oldComponent.getId());
        String message = "Attemped to update fan controller with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated FanController [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }
    
    

}
