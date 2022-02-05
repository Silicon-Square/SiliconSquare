package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "external_storage")
public class ExternalStorage extends Component {

    @Id
    @Column(name = "id_external_storage", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/external_storage.webp";

    @SerializedName("Name")
    @Column(name = "name")
    private String name;

    @SerializedName("Model")
    @Column(name = "model")
    private String model;

    @SerializedName("Manufacturer")
    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "amazon_link_url")
    @SerializedName("Amazon Link")
    private String amazonLinkUrl;

    @SerializedName("Price GB")
    @Column(name = "price_gb")
    private String priceGB;

    @SerializedName("Interface")
    @Column(name = "interface")
    private String _interface;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Capacity")
    @Column(name = "capacity")
    private String capacity;

    @SerializedName("Cache")
    @Column(name = "cache")
    private String cache;

    @SerializedName("RPM")
    @Column(name = "rpm")
    private String rpm;

    @SerializedName("Form Factor")
    @Column(name = "form_factor")
    private String formFactor;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Type")
    @Column(name = "type")
    private String type;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "external_storage_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public ExternalStorage() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPriceGB() {
        return priceGB;
    }

    public void setPriceGB(String priceGB) {
        this.priceGB = priceGB;
    }

    public String get_interface() {
        return _interface;
    }

    public void set_interface(String _interface) {
        this._interface = _interface;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getRpm() {
        return rpm;
    }

    public void setRpm(String rpm) {
        this.rpm = rpm;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        this.idState = state.getId();
    }

    @Override
    public String toString() {
        return "ExternalStorage{" + "idExternalStorage=" + id + ", image='" + image + '\'' + ", priceGB='" + priceGB
                + '\'' + ", _interface='" + _interface + '\'' + ", partN='" + partN + '\'' + ", capacity='" + capacity
                + '\'' + ", cache='" + cache + '\'' + ", rpm='" + rpm + '\'' + ", formFactor='" + formFactor + '\''
                + ", amazonLinkUrl='" + amazonLinkUrl + '\'' + ", manufacturer='" + manufacturer + '\'' + ", color='"
                + color + '\'' + ", name='" + name + '\'' + ", type='" + type + '\'' + ", model='" + model + '\''
                + ", isVisible=" + isVisible + ", state=" + state + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Price GB", priceGB));
        attributeList.add(new Attribute("Interface", _interface));
        attributeList.add(new Attribute("Part Number", partN));
        attributeList.add(new Attribute("Capacity", capacity));
        attributeList.add(new Attribute("Cache", cache));
        attributeList.add(new Attribute("RPM", rpm));
        attributeList.add(new Attribute("Form Factor", formFactor));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("Name", name));
        attributeList.add(new Attribute("Type", type));
        attributeList.add(new Attribute("Model", model));
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
        return ComponentCategory.EXTERNAL_STORAGE.value;
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
        return "external_storage";
    }

}
