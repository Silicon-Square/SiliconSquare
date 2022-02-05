package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.WiredNetworkAdapter;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationWiredNetworkAdapterDAOImpl implements ConfigurationComponentDAO<WiredNetworkAdapter> {
     @Override
    public WiredNetworkAdapter getById(Configuration config, int id) {
        String selectQuery = "SELECT e.* FROM wired_network_adapter e, configuration c, configuration_wired_network_adapter conf_ext WHERE c.id_configuration = conf_ext.id_configuration AND conf_ext.id_wired_network_adapter = e.id_wired_network_adapter AND c.id_configuration = ? AND e.id_wired_network_adapter = ?";
        WiredNetworkAdapter c = Q2Obj.fromSelect(WiredNetworkAdapter.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, WiredNetworkAdapter configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_wired_network_adapter (id_configuration, id_wired_network_adapter) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save wired network adapter with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved WiredNetworkAdapter [" + idComponent + "]  into configuration [" + config.getIdConfiguration()
                    + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_wired_network_adapter WHERE id_configuration = ? AND id_wired_network_adapter = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete wired network adapter with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted WiredNetworkAdapter [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, WiredNetworkAdapter oldComponent, WiredNetworkAdapter newComponent) {
        String updateQuery = "UPDATE configuration_wired_network_adapter SET id_wired_network_adapter = ? WHERE id_configuration = ? AND id_wired_network_adapter = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(), config.getIdConfiguration(),
                oldComponent.getId());
        String message = "Attemped to update wired network adapter with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated WiredNetworkAdapter [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }
}
