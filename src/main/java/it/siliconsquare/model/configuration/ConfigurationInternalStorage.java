package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.InternalStorage;

import javax.persistence.*;

@Entity
@Table(name = "configuration_internal_storage")
public class ConfigurationInternalStorage implements ConfigurationComponent<InternalStorage> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_internal_storage", nullable = false)
    private int idConfigurationInternalStorage;

    @Column(name = "id_internal_storage", nullable = false)
    private int idInternalStorage;

    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @JoinColumn(name = "configuration_internal_storage", updatable = false, insertable = false)
    @ManyToOne
    private InternalStorage internalStorage;

    @Override
    public void setComponent(Component internalStorage) {
        this.internalStorage = (InternalStorage) internalStorage;
        this.idInternalStorage = internalStorage.getId();
    }

    @Override
    public InternalStorage getComponent() {
        return internalStorage;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationInternalStorage) {
        this.idConfigurationInternalStorage = idConfigurationInternalStorage;
    }

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationInternalStorage;
    }

    @Override
    public String toString() {
        return "ConfigurationInternalStorage{" +
                "idConfigurationInternalStorage=" + idConfigurationInternalStorage +
                ", internalStorage=" + internalStorage +
                ", idInternalStorage=" + idInternalStorage +
                ", idConfiguration=" + idConfiguration +
                '}';
    }

}
