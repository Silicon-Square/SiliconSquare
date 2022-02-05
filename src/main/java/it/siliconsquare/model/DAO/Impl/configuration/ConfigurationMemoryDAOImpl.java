package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.Memory;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationMemoryDAOImpl implements ConfigurationComponentDAO<Memory>{

	@Override
    public Memory getById(Configuration config, int id) {
        String selectQuery = "SELECT e.* FROM memory e, configuration c, configuration_memory conf_ext WHERE c.id_configuration = conf_ext.id_configuration AND conf_ext.id_memory = e.id_memory AND c.id_configuration = ? AND e.id_memory = ?";
        Memory c = Q2Obj.fromSelect(Memory.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, Memory configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_memory (id_configuration, id_memory) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save memory with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved Memory [" + idComponent + "]  into configuration [" + config.getIdConfiguration()
                    + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_memory WHERE id_configuration = ? AND id_memory = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete memory with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted Memory [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, Memory oldComponent, Memory newComponent) {
        String updateQuery = "UPDATE configuration_memory SET id_memory = ? WHERE id_configuration = ? AND id_memory = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(), config.getIdConfiguration(),
                oldComponent.getId());
        String message = "Attemped to update memory with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated Memory [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }
    
    
}
