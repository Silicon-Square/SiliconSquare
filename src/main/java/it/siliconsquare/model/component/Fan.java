package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fan")
public class Fan extends Component {

    @Id
    @Column(name = "id_fan", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image", nullable = false)
    private String image = "https://resources.siliconsquare.it/assets/images/component/fan.webp";

    @SerializedName("Name")
    @Column(name = "name", nullable = false)
    private String name;

    @SerializedName("Model")
    @Column(name = "model", nullable = false)
    private String model;

    @SerializedName("Manufacturer")
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @SerializedName("Amazon Link")
    @Column(name = "amazon_link_url", nullable = false)
    private String amazonLinkUrl;

    @SerializedName("Connector")
    @Column(name = "connector", nullable = false)
    private String connector;

    @SerializedName("Features")
    @Column(name = "features", nullable = false)
    private String features;

    @SerializedName("Quantity")
    @Column(name = "quantity", nullable = false)
    private String quantity;

    @SerializedName("Part Number")
    @Column(name = "part_n", nullable = false)
    private String partN;

    @SerializedName("Airflow")
    @Column(name = "airflow", nullable = false)
    private String airflow;

    @SerializedName("Static Pressure")
    @Column(name = "static_pressure", nullable = false)
    private String staticPressure;

    @SerializedName("Controller")
    @Column(name = "controller", nullable = false)
    private String controller;

    @SerializedName("Bearing Type")
    @Column(name = "bearing_type", nullable = false)
    private String bearingType;

    @SerializedName("Noise Level")
    @Column(name = "noise_level", nullable = false)
    private String noiseLevel;

    @SerializedName("RPM")
    @Column(name = "rpm", nullable = false)
    private String rpm;

    @SerializedName("Color")
    @Column(name = "color", nullable = false)
    private String color;

    @SerializedName("Size")
    @Column(name = "size", nullable = false)
    private String size;

    @SerializedName("PWM")
    @Column(name = "pwm", nullable = false)
    private String pwm;

    @SerializedName("LED")
    @Column(name = "led", nullable = false)
    private String led;

    @Column(name = "is_visible", nullable = false)
    private boolean isVisible = true;

    @JoinColumn(name = "fan_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    public Fan() {
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String part_n) {
        this.partN = part_n;
    }

    public String getAirflow() {
        return airflow;
    }

    public void setAirflow(String airflow) {
        this.airflow = airflow;
    }

    public String getStaticPressure() {
        return staticPressure;
    }

    public void setStaticPressure(String staticPressure) {
        this.staticPressure = staticPressure;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getBearingType() {
        return bearingType;
    }

    public void setBearingType(String bearingType) {
        this.bearingType = bearingType;
    }

    public String getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(String noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPwm() {
        return pwm;
    }

    public void setPwm(String pwm) {
        this.pwm = pwm;
    }

    public String getLed() {
        return led;
    }

    public void setLed(String led) {
        this.led = led;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Override
    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
        // this.idState = state.getId();
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Part_N", String.valueOf(partN)));
        attributeList.add(new Attribute("Airflow", String.valueOf(airflow)));
        attributeList.add(new Attribute("Static_Pressure", String.valueOf(staticPressure)));
        attributeList.add(new Attribute("Controller", String.valueOf(controller)));
        attributeList.add(new Attribute("Bearing_Type", String.valueOf(bearingType)));
        attributeList.add(new Attribute("Noise_Level", String.valueOf(noiseLevel)));
        attributeList.add(new Attribute("RPM", String.valueOf(rpm)));
        attributeList.add(new Attribute("Manufacturer", String.valueOf(manufacturer)));
        attributeList.add(new Attribute("Color", String.valueOf(color)));
        attributeList.add(new Attribute("Size", String.valueOf(size)));
        attributeList.add(new Attribute("PWM", String.valueOf(pwm)));
        attributeList.add(new Attribute("LED", String.valueOf(led)));
        attributeList.add(new Attribute("Model", String.valueOf(model)));
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
        return ComponentCategory.FAN.value;
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

    // create toString method
    @Override
    public String toString() {
        return "Fan{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", connector='" + connector + '\'' +
                ", features='" + features + '\'' +
                ", quantity='" + quantity + '\'' +
                ", part_n='" + partN + '\'' +
                ", airflow='" + airflow + '\'' +
                ", static_pressure='" + staticPressure + '\'' +
                ", controller='" + controller + '\'' +
                ", bearing_type='" + bearingType + '\'' +
                ", noise_level='" + noiseLevel + '\'' +
                ", rpm='" + rpm + '\'' +
                ", amazon_link_url='" + amazonLinkUrl + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", color='" + color + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", pwm='" + pwm + '\'' +
                ", led='" + led + '\'' +
                ", isVisible=" + isVisible +
                ", state=" + state +
                ", idState=" + idState +
                '}';
    }

    @Override
    public String getObjectTableName() {
        return "fan";
    }

}
