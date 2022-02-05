package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wired_network_adapter")
public class WiredNetworkAdapter extends Component {

    @Id
    @Column(name = "id_wired_network_adapter", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/wired_network_adapter.webp";

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

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Ports")
    @Column(name = "ports")
    private String ports;

    @SerializedName("Features")
    @Column(name = "features")
    private String features;

    @SerializedName("Interface")
    @Column(name = "interface")
    private String _interface;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "wired_network_adapter_state")
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

    public WiredNetworkAdapter() {
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
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

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WiredNetworkAdapter{" + "idWiredNetworkAdapter=" + id + ", partN='" + partN + '\'' + ", color='" + color
                + '\'' + ", amazonLinkUrl=" + amazonLinkUrl + ", manufacturer='" + manufacturer + '\'' + ", model='"
                + model + '\'' + ", ports='" + ports + '\'' + ", features='" + features + '\'' + ", image=" + image
                + ", _interface='" + _interface + '\'' + ", name='" + name + '\'' + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute("Part Number", partN));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("Ports", ports));
        attributeList.add(new Attribute("Features", features));
        attributeList.add(new Attribute("Interface", _interface));
        return (attributeList);
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
        return ComponentCategory.WIRED_NETWORK_ADAPTER.value;
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
        return "wired_network_adapter";
    }

}
