package it.siliconsquare.model.configuration;

import it.siliconsquare.model.component.Component;
import it.siliconsquare.model.component.SoundCard;

import javax.persistence.*;

@Entity
@Table(name = "configuration_sound_card")
public class ConfigurationSoundCard implements ConfigurationComponent<SoundCard>{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_configuration_sound_card", nullable = false)
    private int idConfigurationSoundCard;

    @Column(name = "id_sound_card", nullable = false)
    private int idSoundCard;

    @Column(name = "id_configuration", nullable = false)
    private int idConfiguration;

    @JoinColumn(name = "configuration_sound_card", updatable = false, insertable = false)
    @ManyToOne
    private SoundCard soundCard;

    @Override
    public SoundCard getComponent() {
        return soundCard;
    }

    @Override
    public void setComponent(Component soundCard) {
        this.soundCard = (SoundCard)soundCard;
        this.idSoundCard = soundCard.getId();
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
      return idConfigurationSoundCard;
    }

    @Override
    public void setIdConfigurationComponent(int idConfigurationComponent) {
        this.idConfigurationSoundCard = idConfigurationComponent;
    }

    @Override
    public String toString() {
        return "ConfigurationSoundCard{" +
                "idConfigurationSoundCard=" + idConfigurationSoundCard +
                ", soundCard=" + soundCard +
                ", idSoundCard=" + idSoundCard +
                ", idConfiguration=" + idConfiguration +
                '}';
    }

  

}
