package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sound_card")
public class SoundCard extends Component {

    @Id
    @Column(name = "id_sound_card", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/sound_card.webp";

    @SerializedName("Name")
    @Column(name = "name")
    private String name;

    @SerializedName("Model")
    @Column(name = "model")
    private String model;

    @SerializedName("Manufacturer")
    @Column(name = "manufacturer")
    private String manufacturer;

    @SerializedName("Amazon Link")
    @Column(name = "amazon_Link_url")
    private String amazonLinkUrl;

    @SerializedName("Chipset")
    @Column(name = "chipset")
    private String chipset;

    @SerializedName("Digital Audio")
    @Column(name = "digital_audio")
    private String digitalAudio;

    @SerializedName("Signal-To-Noise Ratio")
    @Column(name = "signal_to_noise_ratio")
    private String signalToNoiseRatio;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Sample Rate")
    @Column(name = "sample_rate")
    private String sampleRate;

    @SerializedName("Channels")
    @Column(name = "channels")
    private String channels;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("SNR")
    @Column(name = "snr")
    private String snr;

    @SerializedName("Interface")
    @Column(name = "interface")
    private String _interface;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "sound_card_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
        this.idState = state.getId();
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public SoundCard() {
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getDigitalAudio() {
        return digitalAudio;
    }

    public void setDigitalAudio(String digitalAudio) {
        this.digitalAudio = digitalAudio;
    }

    public String getSignalToNoiseRatio() {
        return signalToNoiseRatio;
    }

    public void setSignalToNoiseRatio(String signalToNoiseRatio) {
        this.signalToNoiseRatio = signalToNoiseRatio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSampleRate() {
        return sampleRate;
    }

    public void setSampleRate(String sampleRate) {
        this.sampleRate = sampleRate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getSnr() {
        return snr;
    }

    public void setSnr(String snr) {
        this.snr = snr;
    }

    public String getAmazonLinkUrl() {
        return amazonLinkUrl;
    }

    public void setAmazonLinkUrl(String amazonLinkUrl) {
        this.amazonLinkUrl = amazonLinkUrl;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String get_interface() {
        return _interface;
    }

    public void set_interface(String _interface) {
        this._interface = _interface;
    }

    @Override
    public String toString() {
        return "SoundCard{" + "idSoundCard=" + id + ", chipset='" + chipset + '\'' + ", digitalAudio='" + digitalAudio
                + '\'' + ", signalToNoiseRatio='" + signalToNoiseRatio + '\'' + ", color='" + color + '\''
                + ", sampleRate='" + sampleRate + '\'' + ", image=" + image + ", channels='" + channels + '\''
                + ", name='" + name + '\'' + ", partN='" + partN + '\'' + ", snr='" + snr + '\'' + ", amazonLinkUrl="
                + amazonLinkUrl + ", manufacturer='" + manufacturer + '\'' + ", model='" + model + '\''
                + ", _interface='" + _interface + '\'' + '}';
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public String getAmazonLink() {
        return amazonLinkUrl;
    }

    @Override
    public String getImageLink() {
        return image;
    }

    @Override
    public String getCategory() {
        return ComponentCategory.SOUND_CARD.value;
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributesList = new ArrayList<>();
        attributesList.add(new Attribute("Chipset", chipset));
        attributesList.add(new Attribute("Digital Audio", digitalAudio));
        attributesList.add(new Attribute("Signal to Noise Ratio", signalToNoiseRatio));
        attributesList.add(new Attribute("Color", color));
        attributesList.add(new Attribute("Sample Rate", sampleRate));
        attributesList.add(new Attribute("Channels", channels));
        attributesList.add(new Attribute("Part Number", partN));
        attributesList.add(new Attribute("SNR", snr));
        attributesList.add(new Attribute("Manufacturer", manufacturer));
        attributesList.add(new Attribute("Model", model));
        attributesList.add(new Attribute("Interface", _interface));
        return (attributesList);

    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {
        switch (category) {// PCIe X1 - PCI
            case MOTHERBOARD:
                if (_interface.contains("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCI;
            default:
                break;
        }
        return ProtocolConfiguration.COMPATIBLE;
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("interface", _interface));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "sound_card";
    }

}