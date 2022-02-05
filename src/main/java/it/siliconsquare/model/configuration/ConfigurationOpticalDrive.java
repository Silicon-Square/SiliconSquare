package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.OpticalDrive;

import javax.persistence.*;

@Entity
@Table(name = "configuration_optical_drive")
public class ConfigurationOpticalDrive implements ConfigurationComponent<OpticalDrive> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_optical_drive", nullable = false)
    private int idConfigurationOpticalDrive;

    @Column(name = "id_configuration", nullable = true)
    private Integer idConfiguration;

    @Column(name = "id_optical_drive", nullable = true)
    private Integer idOpticalDrive;

    @JoinColumn(name = "configuration_optical_drive", updatable = false, insertable = false)
    @ManyToOne
    private OpticalDrive opticalDrive;

    @Override
    public void setComponent(Component opticalDrive) {
        this.opticalDrive = (OpticalDrive)opticalDrive;
        this.idOpticalDrive = opticalDrive.getId();
    }

    @Override
    public OpticalDrive getComponent() {
        return opticalDrive;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationopticalDrive) {
        this.idConfigurationOpticalDrive = idConfigurationopticalDrive;
    }

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationOpticalDrive;
    }
    @Override
    public String toString() {
        return "ConfigurationOpticalDrive{" +
                "idConfiguration=" + idConfiguration +
                ", opticalDrive=" + opticalDrive +
                ", idOpticalDrive=" + idOpticalDrive +
                ", idConfigurationOpticalDrive=" + idConfigurationOpticalDrive +
                '}';
    }

}
