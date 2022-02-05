package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.OpticalDrive;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationOpticalDriveDAOImpl implements ConfigurationComponentDAO<OpticalDrive> {
    @Override
    public OpticalDrive getById(Configuration config, int id) {
        String selectQuery = "SELECT e.* FROM optical_drive e, configuration c, configuration_optical_drive conf_ext WHERE c.id_configuration = conf_ext.id_configuration AND conf_ext.id_optical_drive = e.id_optical_drive AND c.id_configuration = ? AND e.id_optical_drive = ?";
        OpticalDrive c = Q2Obj.fromSelect(OpticalDrive.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, OpticalDrive configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_optical_drive (id_configuration, id_optical_drive) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save optical drive with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved OpticalDrive [" + idComponent + "]  into configuration [" + config.getIdConfiguration()
                    + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_optical_drive WHERE id_configuration = ? AND id_optical_drive = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete optical drive with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted OpticalDrive [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, OpticalDrive oldComponent, OpticalDrive newComponent) {
        String updateQuery = "UPDATE configuration_optical_drive SET id_optical_drive = ? WHERE id_configuration = ? AND id_optical_drive = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(), config.getIdConfiguration(),
                oldComponent.getId());
        String message = "Attemped to update optical drive with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated OpticalDrive [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }
}
