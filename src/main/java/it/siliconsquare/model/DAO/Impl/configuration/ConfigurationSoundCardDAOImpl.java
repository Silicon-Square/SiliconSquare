package it.siliconsquare.model.DAO.Impl.configuration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.configuration.ConfigurationComponentDAO;
import it.siliconsquare.model.component.SoundCard;
import it.siliconsquare.model.configuration.Configuration;

public class ConfigurationSoundCardDAOImpl implements ConfigurationComponentDAO<SoundCard>{

    @Override
    public SoundCard getById(Configuration config, int id) {
        String selectQuery = "SELECT s.* FROM sound_card s, configuration c, configuration_sound_card conf_sound_card WHERE c.id_configuration = conf_sound_card.id_configuration AND conf_sound_card.id_sound_card = s.id_sound_card AND c.id_configuration = ? AND s.id_sound_card = ?";
        SoundCard c = Q2Obj.fromSelect(SoundCard.class, selectQuery, config.getIdConfiguration(), id);
        return c;
    }

    @Override
    public boolean save(Configuration config, SoundCard configurationComponent) {
        int idComponent = configurationComponent.getId();
        String saveQuery = "INSERT INTO configuration_sound_card (id_configuration, id_sound_card) VALUES (?, ?)";
        int affectedRows = Q2Sql.executeUpdate(saveQuery, config.getIdConfiguration(), idComponent);

        String message = "Attemped to save sound card with id " + idComponent + " into configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Saved Sound Card [" + idComponent + "]  into configuration [" + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean delete(Configuration config, int id) {
        String deleteQuery = "DELETE FROM configuration_sound_card WHERE id_configuration = ? AND id_sound_card = ?";
        int affectedRows = Q2Sql.executeUpdate(deleteQuery, config.getIdConfiguration(), id);
        String message = "Attemped to delete sound card with id " + id + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Deleted Sound Card [" + id + "]  from configuration [" + config.getIdConfiguration() + "]";  
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }

    @Override
    public boolean update(Configuration config, SoundCard oldComponent, SoundCard newComponent) {
        String updateQuery = "UPDATE configuration_sound_card SET id_sound_card = ? WHERE id_configuration = ? AND id_sound_card = ?";
        int affectedRows = Q2Sql.executeUpdate(updateQuery, newComponent.getId(),
                config.getIdConfiguration(), oldComponent.getId());
        String message = "Attemped to update sound card with id " + oldComponent.getId() + " from configuration "
                + config.getIdConfiguration();
        if (affectedRows > 0)
            message = "Updated Sound Card [" + oldComponent.getId() + " --> " + newComponent.getId()
                    + "]  from configuration ["
                    + config.getIdConfiguration() + "]";
        Logger.getInstance().captureMessage(message);
        return affectedRows > 0;
    }
    
}
