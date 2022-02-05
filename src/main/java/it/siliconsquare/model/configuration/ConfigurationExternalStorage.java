package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.ExternalStorage;

import javax.persistence.*;

@Entity
@Table(name = "configuration_external_storage")
public class ConfigurationExternalStorage implements ConfigurationComponent<ExternalStorage> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_external_storage", nullable = false)
    private int idConfigurationExternalStorage;

    @Column(name = "id_external_storage", nullable = false)
    private int idExternalStorage;

    @JoinColumn(name = "configuration_external_storage")
    @ManyToOne
    private ExternalStorage externalStorage;

    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @Override
    public void setComponent(Component externalStorage) {
        this.externalStorage = (ExternalStorage)externalStorage;
        this.idExternalStorage = externalStorage.getId();
    }

    @Override
    public ExternalStorage getComponent() {
        return externalStorage;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationExternalStorage) {
        this.idConfigurationExternalStorage = idConfigurationExternalStorage;
    }

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationExternalStorage;
    }

    @Override
    public String toString() {
        return "ConfigurationExternalStorage{" +
                "idConfigurationExternalStorage=" + idConfigurationExternalStorage +
                ", idExternalStorage=" + idExternalStorage +
                ", externalStorage=" + externalStorage +
                ", idConfiguration=" + idConfiguration +
                '}';
    }
}
