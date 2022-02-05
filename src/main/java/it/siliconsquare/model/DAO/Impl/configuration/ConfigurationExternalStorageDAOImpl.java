package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.ExternalStorage;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationExternalStorageDAOImpl implements ConfigurationComponentDAO<ExternalStorage> {

    @Override
    public ExternalStorage getById(Configuration config, int id) {
        String selectQuery = "SELECT e.* FROM external_storage e, configuration c, configuration_external_storage conf_ext WHERE c.id_configuration = conf_ext.id_configuration AND conf_ext.id_external_storage = e.id_external_storage AND c.id_configuration = ? AND e.id_external_storage = ?";
        ExternalStorage c = Q2Obj.fromSelect(ExternalStorage.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, ExternalStorage configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_external_storage (id_configuration, id_external_storage) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save external storage with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved ExternalStorage [" + idComponent + "]  into configuration [" + config.getIdConfiguration()
                    + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_external_storage WHERE id_configuration = ? AND id_external_storage = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete external storage with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted ExternalStorage [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, ExternalStorage oldComponent, ExternalStorage newComponent) {
        String updateQuery = "UPDATE configuration_external_storage SET id_external_storage = ? WHERE id_configuration = ? AND id_external_storage = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(), config.getIdConfiguration(),
                oldComponent.getId());
        String message = "Attemped to update external storage with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated ExternalStorage [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

}
