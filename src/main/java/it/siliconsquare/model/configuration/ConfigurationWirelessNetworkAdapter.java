package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.WirelessNetworkAdapter;

import javax.persistence.*;

@Entity
@Table(name = "configuration_wireless_network_adapter")
public class ConfigurationWirelessNetworkAdapter implements ConfigurationComponent<WirelessNetworkAdapter> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_wireless_network_adapter", nullable = false)
    private int idConfigurationWirelessNetworkAdapter;

    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @Column(name = "id_wireless_network_adapter", nullable = false)
    private int idWirelessNetworkAdapter;

    @JoinColumn(name = "configuration_wireless_network_adapter", updatable = false, insertable = false)
    @ManyToOne
    private WirelessNetworkAdapter wirelessNetworkAdapter;

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public WirelessNetworkAdapter getComponent(){
        return wirelessNetworkAdapter;
    }

    @Override
    public void setComponent(Component wirelessNetworkAdapter) {
        this.idWirelessNetworkAdapter = wirelessNetworkAdapter.getId();
        this.wirelessNetworkAdapter = (WirelessNetworkAdapter) wirelessNetworkAdapter;
    }


    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationWirelessNetworkAdapter;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationComponent) {
        this.idConfigurationWirelessNetworkAdapter = idConfigurationComponent;
    }

    
    @Override
    public String toString() {
        return "ConfigurationWirelessNetworkAdapter{" +
                "idConfigurationWirelessNetworkAdapter=" + idConfigurationWirelessNetworkAdapter +
                ", idConfiguration=" + idConfiguration +
                ", idWirelessNetworkAdapter=" + idWirelessNetworkAdapter +
                ", wirelessNetworkAdapter=" + wirelessNetworkAdapter +
                '}';
    }

   

}
