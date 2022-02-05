package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.Fan;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationFanDAOImpl implements ConfigurationComponentDAO<Fan> {

    @Override
    public Fan getById(Configuration config, int id) {
        String selectQuery = "SELECT f.* FROM fan f, configuration c, configuration_fan conf_fan WHERE c.id_configuration = conf_fan.id_configuration AND conf_fan.id_fan = f.id_fan AND c.id_configuration = ? AND f.id_fan = ?";
        Fan c = Q2Obj.fromSelect(Fan.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, Fan configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_fan (id_configuration, id_fan) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save fan with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved Fan [" + idComponent + "]  into configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_fan WHERE id_configuration = ? AND id_fan = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete fan with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted Fan [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, Fan oldComponent, Fan newComponent) {
        String updateQuery = "UPDATE configuration_fan SET id_fan = ? WHERE id_configuration = ? AND id_fan = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(),
                config.getIdConfiguration(), oldComponent.getId());
        String message = "Attemped to update fan with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated Fan [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

}
