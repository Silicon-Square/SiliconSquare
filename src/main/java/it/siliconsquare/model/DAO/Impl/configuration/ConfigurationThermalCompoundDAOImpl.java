package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.ThermalCompound;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationThermalCompoundDAOImpl implements ConfigurationComponentDAO<ThermalCompound> {
    @Override
    public ThermalCompound getById(Configuration config, int id) {
        String selectQuery = "SELECT e.* FROM thermal_compound e, configuration c, configuration_thermal_compound conf_ext WHERE c.id_configuration = conf_ext.id_configuration AND conf_ext.id_thermal_compound = e.id_thermal_compound AND c.id_configuration = ? AND e.id_thermal_compound = ?";
        ThermalCompound c = Q2Obj.fromSelect(ThermalCompound.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, ThermalCompound configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_thermal_compound (id_configuration, id_thermal_compound) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save external storage with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved ThermalCompound [" + idComponent + "]  into configuration [" + config.getIdConfiguration()
                    + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_thermal_compound WHERE id_configuration = ? AND id_thermal_compound = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete thermal compound with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted ThermalCompound [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, ThermalCompound oldComponent, ThermalCompound newComponent) {
        String updateQuery = "UPDATE configuration_thermal_compound SET id_thermal_compound = ? WHERE id_configuration = ? AND id_thermal_compound = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(), config.getIdConfiguration(),
                oldComponent.getId());
        String message = "Attemped to update external storage with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated ThermalCompound [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }
}
