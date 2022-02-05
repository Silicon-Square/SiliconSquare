package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.Fan;

import javax.persistence.*;

@Entity
@Table(name = "configuration_fan")
public class ConfigurationFan implements ConfigurationComponent<Fan> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_fan", nullable = false)
    private int idConfigurationFan;

    @Column(name = "id_configuration", nullable = true)
    private Integer idConfiguration;

    @Column(name = "id_fan", nullable = true)
    private Integer idFan;

    @JoinColumn(name = "configuration_fan")
    @ManyToOne
    private Fan fan;

    @Override
    public void setComponent(Component fan) {
        this.fan = (Fan)fan;
        this.idFan = fan.getId();
    }

    @Override
    public Fan getComponent() {
        return fan;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationFan) {
        this.idConfigurationFan = idConfigurationFan;
    }

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationFan;
    }

    @Override
    public String toString() {
        return "ConfigurationFan{" +
                "idConfigurationFan=" + idConfigurationFan +
                ", idConfiguration=" + idConfiguration +
                ", idFan=" + idFan +
                ", fan=" + fan +
                '}';
    }

}
