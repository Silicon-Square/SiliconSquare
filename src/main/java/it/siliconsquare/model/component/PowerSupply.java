package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.HtmlVisualizer;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "power_supply")
public class PowerSupply extends Component {

    @Id
    @Column(name = "id_power_supply", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/power_supply.webp";

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

    @SerializedName("Efficiency")
    @Column(name = "efficiency")
    private String efficiency;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Length")
    @Column(name = "length")
    private String length;

    @SerializedName("Fanless")
    @Column(name = "fanless")
    private String fanless;

    @SerializedName("Sata Connectors")
    @Column(name = "sata_connectors")
    private String sataConnectors;

    @SerializedName("ATX Connectors")
    @Column(name = "atx_connectors")
    private String atxConnectors;

    @SerializedName("Molex 4-pin Connectors")
    @Column(name = "molex_4_pin_connectors")
    private String molex4PinConnectors;

    @SerializedName("PCIe 12-pin Connectors")
    @Column(name = "pcie_12_pin_connectors")
    private String pcie12PinConnectors;

    @SerializedName("Output")
    @Column(name = "output")
    private String output;

    @SerializedName("PCIe 6-pin Connectors")
    @Column(name = "pcie_6_pin_connectors")
    private String pcie6PinConnectors;

    @SerializedName("EPS Connectors")
    @Column(name = "eps_connectors")
    private String epsConnectors;

    @SerializedName("Form Factor")
    @Column(name = "form_factor")
    private String formFactor;

    @SerializedName("Wattage")
    @Column(name = "wattage")
    private String wattage;

    @SerializedName("Efficiency Rating")
    @Column(name = "efficiency_rating")
    private String efficiencyRating;

    @SerializedName("Modular")
    @Column(name = "modular")
    private String modular;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Type")
    @Column(name = "type")
    private String type;

    @SerializedName("PCIe 6+2-pin Connectors")
    @Column(name = "pcie_6_2_pin_connectors")
    private String pcie6Pin2Connectors;

    @SerializedName("PCIe 6+4-pin Connectors")
    @Column(name = "pcie_8_pin_connectors")
    private String pcie8PinConnectors;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "power_supply_state")
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

    public PowerSupply() {

    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getFanless() {
        return fanless;
    }

    public void setFanless(String fanless) {
        this.fanless = fanless;
    }

    public String getSataConnectors() {
        return sataConnectors;
    }

    public void setSataConnectors(String sataConnectors) {
        this.sataConnectors = sataConnectors;
    }

    public String getAtxConnectors() {
        return atxConnectors;
    }

    public void setAtxConnectors(String atxConnectors) {
        this.atxConnectors = atxConnectors;
    }

    public String getMolex4PinConnectors() {
        return molex4PinConnectors;
    }

    public void setMolex4PinConnectors(String molex4PinConnectors) {
        this.molex4PinConnectors = molex4PinConnectors;
    }

    public String getPcie12PinConnectors() {
        return pcie12PinConnectors;
    }

    public void setPcie12PinConnectors(String pcie12PinConnectors) {
        this.pcie12PinConnectors = pcie12PinConnectors;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getPcie6PinConnectors() {
        return pcie6PinConnectors;
    }

    public void setPcie6PinConnectors(String pcie6PinConnectors) {
        this.pcie6PinConnectors = pcie6PinConnectors;
    }

    public String getEpsConnectors() {
        return epsConnectors;
    }

    public void setEpsConnectors(String epsConnectors) {
        this.epsConnectors = epsConnectors;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getWattage() {
        return wattage;
    }

    public void setWattage(String wattage) {
        this.wattage = wattage;
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

    public String getEfficiencyRating() {
        return efficiencyRating;
    }

    public void setEfficiencyRating(String efficiencyRating) {
        this.efficiencyRating = efficiencyRating;
    }

    public String getModular() {
        return modular;
    }

    public void setModular(String modular) {
        this.modular = modular;
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

    public String getPcie6Pin2Connectors() {
        return pcie6Pin2Connectors;
    }

    public void setPcie6Pin2Connectors(String pcie6Pin2Connectors) {
        this.pcie6Pin2Connectors = pcie6Pin2Connectors;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPcie8PinConnectors() {
        return pcie8PinConnectors;
    }

    public void setPcie8PinConnectors(String pcie8PinConnectors) {
        this.pcie8PinConnectors = pcie8PinConnectors;
    }

    @Override
    public String toString() {
        return "PowerSupply{" + "id_power_supply=" + id + ", efficiency='" + efficiency + '\'' + ", image='" + image
                + '\'' + ", part_n='" + partN + '\'' + ", length='" + length + '\'' + ", fanless='" + fanless + '\''
                + ", sataConnectors='" + sataConnectors + '\'' + ", atxConnectors='" + atxConnectors + '\''
                + ", molex4PinConnectors='" + molex4PinConnectors + '\'' + ", pcie12PinConnectors='"
                + pcie12PinConnectors + '\'' + ", output='" + output + '\'' + ", pcie6PinConnectors='"
                + pcie6PinConnectors + '\'' + ", epsConnectors='" + epsConnectors + '\'' + ", formFactor='" + formFactor
                + '\'' + ", wattage='" + wattage + '\'' + ", amazonLinkUrl='" + amazonLinkUrl + '\''
                + ", manufacturer='" + manufacturer + '\'' + ", efficiencyRating='" + efficiencyRating + '\''
                + ", modular='" + modular + '\'' + ", color='" + color + '\'' + ", name='" + name + '\'' + ", type='"
                + type + '\'' + ", pcie6Pin2Connectors='" + pcie6Pin2Connectors + '\'' + ", model='" + model + '\''
                + ", pcie8PinConnectors='" + pcie8PinConnectors + '\'' + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Efficiency", efficiency));
        attributeList.add(new Attribute("Part Number", partN));
        attributeList.add(new Attribute("Length", length));
        attributeList.add(new Attribute("Fanless", fanless));
        attributeList.add(new Attribute("SATA Connectors", sataConnectors));
        attributeList.add(new Attribute("ATX Connectors", atxConnectors));
        attributeList.add(new Attribute("Molex 4-Pin Connectors", molex4PinConnectors));
        attributeList.add(new Attribute("PCIe 12-Pin Connectors", pcie12PinConnectors));
        attributeList.add(new Attribute("Output", output));
        attributeList.add(new Attribute("PCIe 6-Pin Connectors", pcie6PinConnectors));
        attributeList.add(new Attribute("EPS Connectors", epsConnectors));
        attributeList.add(new Attribute("Form Factor", formFactor));
        attributeList.add(new Attribute("Wattage", wattage));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Efficiency Rating", efficiencyRating));
        attributeList.add(new Attribute("Modular", modular));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("Type", type));
        attributeList.add(new Attribute("PCIe 6-Pin 2 Connectors", pcie6Pin2Connectors));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("PCIe 8-Pin Connectors", pcie8PinConnectors));
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
        return ComponentCategory.POWER_SUPPLY.value;
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
        switch (category) {
            case MOTHERBOARD:
                boolean isCompatible = HtmlVisualizer.containsKeywords(allParameters, "form_factor", formFactor, "\\n",
                        false);
                if (!isCompatible)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
            default:
                break;
        }

        return ProtocolConfiguration.COMPATIBLE;
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("form_factor", formFactor));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "power_supply";
    }

    @Override
    public boolean canBeAddedMultipleTimes() {
        return false;
    };
}
