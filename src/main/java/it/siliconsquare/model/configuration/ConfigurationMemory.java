package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.Memory;

import javax.persistence.*;

@Entity
@Table(name = "configuration_memory")
public class ConfigurationMemory implements ConfigurationComponent<Memory> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_memory", nullable = false)
    private int idConfigurationMemory;

    @Column(name = "id_configuration", nullable = false)
    private Integer idConfiguration;

    @Column(name = "id_memory", nullable = false) 
    private int idMemory;

    @JoinColumn(name = "configuration_memory", updatable = false, insertable = false)
    @ManyToOne
    private Memory memory;

    @Override
    public void setComponent(Component component) {
        this.memory = (Memory) component;
        this.idMemory = ((Memory) component).getId();
        this.memory.setId(idMemory);
        System.out.println("Component of setComponent: " + memory);
    }

    @Override
    public Memory getComponent() {
        return memory;
    }

    public int getIdMemory() {
        return idMemory;
    }

    @Override
    public void setIdConfiguration(int idConfiguration) {
        this.idConfiguration = idConfiguration;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationMemory) {
        this.idConfigurationMemory = idConfigurationMemory;
    }

    @Override
    public int getIdConfiguration() {
        return idConfiguration;
    }

    @Override
    public int getIdConfigurationComponent() {
        return idConfigurationMemory;
    }

    @Override
    public String toString() {
        return "ConfigurationMemory{" +
                "idConfigurationMemory=" + idConfigurationMemory +
                ", idConfiguration=" + idConfiguration +
                ", idMemory=" + idMemory +
                ", memory=" + memory +
                '}';
    }

   
}
