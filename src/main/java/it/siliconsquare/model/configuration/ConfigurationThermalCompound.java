package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.ThermalCompound;

import javax.persistence.*;

@Entity
@Table(name = "configuration_thermal_compound")
public class ConfigurationThermalCompound implements ConfigurationComponent<ThermalCompound> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuation_thermal_compound", nullable = false)
    private int idConfiguationThermalCompound;

    @Column(name = "id_thermal_compound", nullable = false)
    private int idThermalCompound;

    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @JoinColumn(name = "configuration_thermal_compound", updatable = false, insertable = false)
    @ManyToOne
    private ThermalCompound thermalCompound;

    @Override
    public ThermalCompound getComponent() {
        return thermalCompound;
    }

    @Override
    public void setComponent(Component thermalCompound) {
        this.idThermalCompound = thermalCompound.getId();
        this.thermalCompound = (ThermalCompound) thermalCompound;
    }

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }


    @Override
    public int getIdConfigurationComponent() {
        return idConfiguationThermalCompound;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationComponent) {
        this.idConfiguationThermalCompound = idConfigurationComponent;   
    }

    @Override
    public String toString() {
        return "ConfigurationThermalCompound{" +
                "idConfiguationThermalCompound=" + idConfiguationThermalCompound +
                ", thermalCompound=" + thermalCompound +
                ", idThermalCompound=" + idThermalCompound +
                ", idConfiguration=" + idConfiguration +
                '}';
    }
}

    
