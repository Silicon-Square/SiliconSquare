package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.WiredNetworkAdapter;

import javax.persistence.*;

@Entity
@Table(name = "configuration_wired_network_adapter")
public class ConfigurationWiredNetworkAdapter implements ConfigurationComponent<WiredNetworkAdapter> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_wired_network_adapter", nullable = false)
    private int idConfigurationWiredNetworkAdapter;

    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @Column(name = "id_wired_network_adapter", nullable = false)
    private int idWiredNetworkAdapter;

    @JoinColumn(name = "configuration_wired_network_adapter", updatable = false, insertable = false)
    @ManyToOne
    private WiredNetworkAdapter wiredNetworkAdapter;

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public WiredNetworkAdapter getComponent() {
        return wiredNetworkAdapter;
    }

    @Override
    public void setComponent(Component wiredNetworkAdapter) {
        this.idWiredNetworkAdapter = wiredNetworkAdapter.getId();
        this.wiredNetworkAdapter = (WiredNetworkAdapter) wiredNetworkAdapter;
    }

    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationWiredNetworkAdapter;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationComponent) {
        this.idConfigurationWiredNetworkAdapter = idConfigurationComponent;
    }
    
    @Override
    public String toString() {
        return "ConfigurationWiredNetworkAdapter{" +
                "idConfigurationWiredNetworkAdapter=" + idConfigurationWiredNetworkAdapter +
                ", idConfiguration=" + idConfiguration +
                ", idWiredNetworkAdapter=" + idWiredNetworkAdapter +
                ", wiredNetworkAdapter=" + wiredNetworkAdapter +
                '}';
    }

}
