package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.InternalStorage;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationInternalStorageDAOImpl implements ConfigurationComponentDAO<InternalStorage>{

    @Override
    public InternalStorage getById(Configuration config, int id) {
        String selectQuery = "SELECT i.* FROM internal_storage i, configuration c, configuration_internal_storage conf_int_sto WHERE c.id_configuration = conf_int_sto.id_configuration AND conf_int_sto.id_internal_storage = i.id_internal_storage AND c.id_configuration = ? AND i.id_internal_storage = ?";
        InternalStorage c = Q2Obj.fromSelect(InternalStorage.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, InternalStorage configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_internal_storage (id_configuration, id_internal_storage) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save internal storage with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved Internal Storage [" + idComponent + "]  into configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);

        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_internal_storage WHERE id_configuration = ? AND id_internal_storage = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete internal storage with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted Internal Storage [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, InternalStorage oldConfigurationComponent,
            InternalStorage newConfigurationComponent) {
        String updateQuery = "UPDATE configuration_internal_storage SET id_internal_storage = ? WHERE id_configuration = ? AND id_internal_storage = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newConfigurationComponent.getId(), config.getIdConfiguration(), oldConfigurationComponent.getId());
        String message = "Attemped to update internal storage with id " + oldConfigurationComponent.getId()
                + " from configuration " + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated Internal Storage [" + oldConfigurationComponent.getId() + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }
    
}
