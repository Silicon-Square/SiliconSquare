package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fan_controller")
public class FanController extends Component {

    @Id
    @Column(name = "id_fan_controller", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/fan_controller.webp";

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
    @Column(name = "amazon_link_url")
    private String amazonLinkUrl;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Channel Wattage")
    @Column(name = "channel_wattage")
    private String channelWattage;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Form Factor")
    @Column(name = "form_factor")
    private String formFactor;

    @SerializedName("Features")
    @Column(name = "features")
    private String features;

    @SerializedName("PWM 4-pin")
    @Column(name = "pwm_4pin")
    private String pwm;

    @SerializedName("Channels")
    @Column(name = "channels")
    private String channels;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "fan_controller_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
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

    public FanController() {
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getChannelWattage() {
        return channelWattage;
    }

    public void setChannelWattage(String channelWattage) {
        this.channelWattage = channelWattage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getPwm() {
        return pwm;
    }

    public void setPwm(String pwm) {
        this.pwm = pwm;
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

    @Override
    public String toString() {
        return "FanController{" + "idFanController=" + id + ", partN='" + partN + '\'' + ", channelWattage='"
                + channelWattage + '\'' + ", color='" + color + '\'' + ", amazonLinkUrl='" + amazonLinkUrl + '\''
                + ", manufacturer='" + manufacturer + '\'' + ", model='" + model + '\'' + ", formFactor='" + formFactor
                + '\'' + ", features='" + features + '\'' + ", pwm='" + pwm + '\'' + ", image='" + image + '\''
                + ", channels='" + channels + '\'' + ", name='" + name + '\'' + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Part Number", String.valueOf(partN)));
        attributeList.add(new Attribute("Channel Wattage", String.valueOf(channelWattage)));
        attributeList.add(new Attribute("Color", String.valueOf(color)));
        attributeList.add(new Attribute("Manufacturer", String.valueOf(manufacturer)));
        attributeList.add(new Attribute("Model", String.valueOf(model)));
        attributeList.add(new Attribute("Form Factor", String.valueOf(formFactor)));
        attributeList.add(new Attribute("Features", String.valueOf(features)));
        attributeList.add(new Attribute("PWM", String.valueOf(pwm)));
        attributeList.add(new Attribute("Channels", String.valueOf(channels)));
        return attributeList;
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
        return ComponentCategory.FAN_CONTROLLER.value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {
        return ProtocolConfiguration.COMPATIBLE;
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "fan_controller";
    }
}
