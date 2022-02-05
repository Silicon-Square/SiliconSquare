package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.FanController;

import javax.persistence.*;

@Entity
@Table(name = "configuration_fan_controller")
public class ConfigurationFanController implements ConfigurationComponent<FanController>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_fan_controller", nullable = false)
    private int idConfigurationFanController;

    @Column(name = "id_fan_controller", nullable = false)
    private int idFanController;

    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @JoinColumn(name = "configuration_fan_controller", updatable = false, insertable = false)
    @ManyToOne
    private FanController fanController;

  @Override
    public void setComponent(Component fanController) {
        this.fanController = (FanController)fanController;
        this.idFanController = fanController.getId();
    }

    @Override
    public FanController getComponent() {
        return fanController;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationFanController) {
        this.idConfigurationFanController = idConfigurationFanController;
    }

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationFanController;
    }

    @Override
    public String toString() {
        return "ConfigurationFanController{" +
                "idConfigurationFanController=" + idConfigurationFanController +
                ", fanController=" + fanController +
                ", idFanController=" + idFanController +
                ", idConfiguration=" + idConfiguration +
                '}';
    }

}
