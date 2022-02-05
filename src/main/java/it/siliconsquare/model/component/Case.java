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
@Table(name = "public.case")
public class Case extends Component {
    @Id
    @Column(name = "id_case", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/case.webp";

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

    @SerializedName("Internal 2.5 Bays")
    @Column(name = "internal_2_5_bays")
    private String internal2_5Bays;

    @Column(name = "side_panel_window")
    @SerializedName("Side Panel Window")
    private String sidePanelWindow;

    @SerializedName("Internal 3.5 Bays")
    @Column(name = "internal_3_5_bays")
    private String internal3_5Bays;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Power Supply")
    @Column(name = "power_supply")
    private String powerSupply;

    @SerializedName("Maximum video card length")
    @Column(name = "maximum_video_card_length")
    private String maximumVideoCardLength;

    @SerializedName("Half-height expansion slots")
    @Column(name = "half_height_expansion_slots")
    private String halfHeightExpansionSlots;

    @SerializedName("Dimensions")
    @Column(name = "dimensions")
    private String dimensions;

    @SerializedName("Front Panel USB")
    @Column(name = "front_panel_usb")
    private String frontPanelUSB;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Type")
    @Column(name = "type")
    private String type;

    @SerializedName("External 5.25 Bays")
    @Column(name = "external_5_25_bays")
    private String external5_2_5Bays;

    @SerializedName("Volume")
    @Column(name = "volume")
    private String volume;

    @SerializedName("Power Supply Shroud")
    @Column(name = "power_supply_shroud")
    private String powerSupplyShroud;

    @SerializedName("Full Height External Slots")
    @Column(name = "full_height_expansion_slots")
    private String fullHeightExpansionSlots;

    @SerializedName("Motherboard Form Factor")
    @Column(name = "motherboard_form_factor")
    private String motherboardFormFactor;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "case_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    @Override
    public void setState(State state) {
        this.state = state;
        this.idState = state.getId();
    }

    public String getInternal2_5Bays() {
        return internal2_5Bays;
    }

    public String getInternal3_5Bays() {
        return internal3_5Bays;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public void setInternal2_5Bays(String internal2_5Bays) {
        this.internal2_5Bays = internal2_5Bays;
    }

    public void setInternal3_5Bays(String internal3_5Bays) {
        this.internal3_5Bays = internal3_5Bays;
    }

    public Case() {
    }

    public String getInternal25Bays() {
        return internal2_5Bays;
    }

    public void setInternal25Bays(String internal2_5Bays) {
        this.internal2_5Bays = internal2_5Bays;
    }

    public String getSidePanelWindow() {
        return sidePanelWindow;
    }

    public void setSidePanelWindow(String sidePanelWindow) {
        this.sidePanelWindow = sidePanelWindow;
    }

    public String getInternal35Bays() {
        return internal3_5Bays;
    }

    public void setInternal35Bays(String internal3_5Bays) {
        this.internal3_5Bays = internal3_5Bays;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public String getMaximumVideoCardLength() {
        return maximumVideoCardLength;
    }

    public void setMaximumVideoCardLength(String maximumVideoCardLength) {
        this.maximumVideoCardLength = maximumVideoCardLength;
    }

    public String getHalfHeightExpansionSlots() {
        return halfHeightExpansionSlots;
    }

    public void setHalfHeightExpansionSlots(String halfHeightExpansionSlots) {
        this.halfHeightExpansionSlots = halfHeightExpansionSlots;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFrontPanelUSB() {
        return frontPanelUSB;
    }

    public void setFrontPanelUSB(String frontPanelUSB) {
        this.frontPanelUSB = frontPanelUSB;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getExternal5_2_5Bays() {
        return external5_2_5Bays;
    }

    public void setExternal5_2_5Bays(String external5_2_5Bays) {
        this.external5_2_5Bays = external5_2_5Bays;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
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

    public String getPowerSupplyShroud() {
        return powerSupplyShroud;
    }

    public void setPowerSupplyShroud(String powerSupplyShroud) {
        this.powerSupplyShroud = powerSupplyShroud;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFullHeightExpansionSlots() {
        return fullHeightExpansionSlots;
    }

    public void setFullHeightExpansionSlots(String fullHeightExpansionSlots) {
        this.fullHeightExpansionSlots = fullHeightExpansionSlots;
    }

    public String getMotherboardFormFactor() {
        return motherboardFormFactor;
    }

    public void setMotherboardFormFactor(String motherboardFormFactor) {
        this.motherboardFormFactor = motherboardFormFactor;
    }

    @Override
    public String toString() {
        return "{" + "idCase=" + id + ", internal2_5Bays='" + internal2_5Bays + '\'' + ", sidePanelWindow='"
                + sidePanelWindow + '\'' + ", internal3_5Bays='" + internal3_5Bays + '\'' + ", color='" + color + '\''
                + ", powerSupply='" + powerSupply + '\'' + ", maximumVideoCardLength='" + maximumVideoCardLength + '\''
                + ", halfHeightExpansionSlots='" + halfHeightExpansionSlots + '\'' + ", dimensions='" + dimensions
                + '\'' + ", image=" + image + ", name='" + name + '\'' + ", frontPanelUSB='" + frontPanelUSB + '\''
                + ", partN='" + partN + '\'' + ", type='" + type + '\'' + ", external5_2_5Bays='" + external5_2_5Bays
                + '\'' + ", volume='" + volume + '\'' + ", amazonLinkUrl=" + amazonLinkUrl + ", manufacturer='"
                + manufacturer + '\'' + ", powerSupplyShroud='" + powerSupplyShroud + '\'' + ", model='" + model + '\''
                + ", fullHeightExpansionSlots='" + fullHeightExpansionSlots + '\'' + ", motherboardFormFactor='"
                + motherboardFormFactor + "\'}";
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Internal 2.5 Bays", internal2_5Bays));
        attributeList.add(new Attribute("Side Panel Window", sidePanelWindow));
        attributeList.add(new Attribute("Internal 3.5 Bays", internal3_5Bays));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("Power Supply", powerSupply));
        attributeList.add(new Attribute("Maximum Video Card Length", maximumVideoCardLength));
        attributeList.add(new Attribute("Half Height Expansion Slots", halfHeightExpansionSlots));
        attributeList.add(new Attribute("Dimensions", dimensions));
        attributeList.add(new Attribute("Name", name));
        attributeList.add(new Attribute("Front Panel USB", frontPanelUSB));
        attributeList.add(new Attribute("Part N", partN));
        attributeList.add(new Attribute("Type", type));
        attributeList.add(new Attribute("External 5.2.5 Bays", external5_2_5Bays));
        attributeList.add(new Attribute("Volume", volume));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Power Supply Shroud", powerSupplyShroud));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("Full Height Expansion Slots", fullHeightExpansionSlots));
        attributeList.add(new Attribute("Motherboard Form Factor", motherboardFormFactor));
        return (attributeList);

    }

    @Override
    public String getTitle() {
        return this.name;
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
        return ComponentCategory.CASE.value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {

        switch (category) {
            case MOTHERBOARD:
                boolean isCompatible = HtmlVisualizer.containsKeywords(allParameters, "form_factor",
                        motherboardFormFactor, "\\n", false);
                if (!isCompatible)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                break;
            case CPU_COOLER:
                int caseHeight = HtmlVisualizer.getDigitsFound(dimensions, 2);
                int cpuCoolerHeight = -1;
                for (Attribute attr : allParameters) {
                    if (!attr.name.equalsIgnoreCase("height") || attr.value == null)
                        continue;
                    cpuCoolerHeight = HtmlVisualizer.getDigitsFound(attr.value, 0);
                }
                if (caseHeight < cpuCoolerHeight || cpuCoolerHeight == -1)
                    return ProtocolConfiguration.CPU_COOLER_NOT_COMPATIBLE;
                break;
            case VIDEO_CARD:
                int caseDim = Integer.parseInt(maximumVideoCardLength);
                int videoCardWidth = -1;
                for (Attribute attr : allParameters) {
                    if (!attr.name.equalsIgnoreCase("length") || attr.value == null)
                        continue;
                    videoCardWidth = HtmlVisualizer.getDigitsFound(attr.value, 0);
                }
                if (caseDim < videoCardWidth || videoCardWidth == -1)
                    return ProtocolConfiguration.VIDEO_CARD_NOT_COMPATIBLE;
                break;

            // case POWER_SUPPLY:

            default:
                break;
        }

        return ProtocolConfiguration.COMPATIBLE;
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("dimensions", dimensions));
        attributeList.add(new Attribute("maximum_video_card_length", maximumVideoCardLength));
        attributeList.add(new Attribute("motherboard_form_factor", motherboardFormFactor));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "public.case";
    }

    @Override
    public boolean canBeAddedMultipleTimes() {
        return false;
    }

}
