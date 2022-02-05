package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wireless_network_adapter")
public class WirelessNetworkAdapter extends Component {

    public WirelessNetworkAdapter() {

    }

    @Id
    @Column(name = "id_wireless_network_adapter", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/wireless_network_adapter.webp";

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

    @SerializedName("Operating Range")
    @Column(name = "operating_range")
    private String operatingRange;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Secuirty")
    @Column(name = "security")
    private String security;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Features")
    @Column(name = "features")
    private String features;

    @SerializedName("Protocol")
    @Column(name = "protocol")
    private String protocol;

    @SerializedName("Interface")
    @Column(name = "interface")
    private String _interface;

    @SerializedName("Antenna")
    @Column(name = "antenna")
    private String antenna;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "wireless_network_adapter_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    @Override
    public void setId(int id) {
        this.id = id;

    }

    @Override
    public void setState(State state) {
        this.state = state;
        this.idState = state.getId();

    }

    public State getState() {
        return state;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public String getOperatingRange() {
        return operatingRange;
    }

    public void setOperatingRange(String operatingRange) {
        this.operatingRange = operatingRange;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
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

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String get_interface() {
        return _interface;
    }

    public void set_interface(String _interface) {
        this._interface = _interface;
    }

    public String getAntenna() {
        return antenna;
    }

    public void setAntenna(String antenna) {
        this.antenna = antenna;
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
        return ComponentCategory.WIRELESS_NETWORK_ADAPTER.value;
    }

    @Override
    public String toString() {
        return "WirelessNetworkAdapter{" + "idWirelessNetworkAdapter=" + id + ", operatingRange='" + operatingRange
                + '\'' + ", color='" + color + '\'' + ", image=" + image + ", security='" + security + '\'' + ", name='"
                + name + '\'' + ", partN='" + partN + '\'' + ", amazonLinkUrl=" + amazonLinkUrl + ", manufacturer='"
                + manufacturer + '\'' + ", model='" + model + '\'' + ", features='" + features + '\'' + ", protocol='"
                + protocol + '\'' + ", _interface='" + _interface + '\'' + ", antenna='" + antenna + '\'' + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute("Operating Range", operatingRange));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("Security", security));
        attributeList.add(new Attribute("Name", name));
        attributeList.add(new Attribute("Part Number", partN));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("Features", features));
        attributeList.add(new Attribute("Protocol", protocol));
        attributeList.add(new Attribute("Interface", _interface));
        attributeList.add(new Attribute("Antenna", antenna));
        return attributeList;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {
        // TODO: count pci slots available in motherboard.
        // e.g. is I have a wired adapter with PCIe x4 interface, it can be put in
        // a motherboard with PCIe x4 interface, or above (PCIe x8, PCIe x16 etc)

        switch (category) {
            case MOTHERBOARD:
                if (_interface.contains("pcie x8"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X8;
                else if (_interface.contains("pcie x4"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X4;
                else if (_interface.contains("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else if (_interface.contains("pcie-x"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X;
                else if (_interface.contains("mini-pcie"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_MINI_PCIE;
                else if (_interface.contains("pci"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCI;
                else
                    break;
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
        return "wireless_network_adapter";
    }

}
