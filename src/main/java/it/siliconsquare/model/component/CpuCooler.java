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
@Table(name = "cpu_cooler")
public class CpuCooler extends Component {

    @Id
    @Column(name = "id_cpu_cooler", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/cpu_cooler.webp";

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

    @SerializedName("Bearing")
    @Column(name = "bearing")
    private String bearing;

    @SerializedName("Noise Level")
    @Column(name = "noise_level")
    private String noiseLevel;

    @SerializedName("CPU Socket")
    @Column(name = "cpu_socket")
    private String cpuSocket;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Fan RPM")
    @Column(name = "fan_rpm")
    private String fanRpm;

    @SerializedName("Fanless")
    @Column(name = "fanless")
    private String fanless;

    @SerializedName("Water Cooled")
    @Column(name = "water_cooled")
    private String waterCooled;

    @SerializedName("Height")
    @Column(name = "height")
    private String height;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "cpu_cooler_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    public State getState() {
        return state;
    }

    @Override
    public void setId(int id) {
        this.id=id;
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

    public CpuCooler() {

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBearing() {
        return bearing;
    }

    public void setBearing(String bearing) {
        this.bearing = bearing;
    }

    public String getNoiseLevel() {
        return noiseLevel;
    }

    public void setNoiseLevel(String noiseLevel) {
        this.noiseLevel = noiseLevel;
    }

    public String getCpuSocket() {
        return cpuSocket;
    }

    public void setCpuSocket(String cpuSocket) {
        this.cpuSocket = cpuSocket;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getFanRpm() {
        return fanRpm;
    }

    public void setFanRpm(String fanRpm) {
        this.fanRpm = fanRpm;
    }

    public String getFanless() {
        return fanless;
    }

    public void setFanless(String fanless) {
        this.fanless = fanless;
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

    public String getWaterCooled() {
        return waterCooled;
    }

    public void setWaterCooled(String waterCooled) {
        this.waterCooled = waterCooled;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "CpuCooler{" + "idCpuCooler=" + id + ", color='" + color + '\'' + ", image='" + image + '\'' + ", name='"
                + name + '\'' + ", bearing='" + bearing + '\'' + ", noiseLevel='" + noiseLevel + '\'' + ", cpuSocket='"
                + cpuSocket + '\'' + ", partN='" + partN + '\'' + ", fanRpm='" + fanRpm + '\'' + ", fanless='" + fanless
                + '\'' + ", amazonLinkUrl='" + amazonLinkUrl + '\'' + ", manufacturer='" + manufacturer + '\''
                + ", model='" + model + '\'' + ", waterCooled='" + waterCooled + '\'' + ", height='" + height + '\''
                + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("Bearing", bearing));
        attributeList.add(new Attribute("Noise Level", noiseLevel));
        attributeList.add(new Attribute("CPU Socket", cpuSocket));
        attributeList.add(new Attribute("Part Number", partN));
        attributeList.add(new Attribute("Fan RPM", fanRpm));
        attributeList.add(new Attribute("Fanless", fanless));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("Water Cooled", waterCooled));
        attributeList.add(new Attribute("Height", height));
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
        return ComponentCategory.CPU_COOLER.value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {
        boolean isCompatible = false;

        switch (category) {
            case CPU:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "socket", cpuSocket,
                        "\\n", true);
                if (!isCompatible)
                    return ProtocolConfiguration.CPU_NOT_COMPATIBLE;
                break;
            case MOTHERBOARD:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "socket_cpu", cpuSocket,
                        "\\n", true);
                if (!isCompatible)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                break;
            case CASE:
                int cpuCoolerHeight = -1;
                int cpuCoolerWidth = -1;
                if (waterCooled.contains("No")) // if cpuCooler is air cooled, consider cpuCoolerHeight --> confront
                                                // with heigth of case (L x W x H)
                    cpuCoolerHeight = HtmlVisualizer.getDigitsFound(height, 0);
                else if (waterCooled != null && waterCooled.contains("Yes"))// if cpuCooler is water cooled, consider
                                                                            // cpuCoolerWidth --> confront with width of
                                                                            // case (L x W x H)
                    cpuCoolerWidth = HtmlVisualizer.getDigitsFound(waterCooled, 0);

                String caseLengthString = null;
                String caseWidthString = null;
                for (var attr : allParameters)
                    if (attr.name.equalsIgnoreCase("dimensions")) {
                        caseLengthString = attr.getValue();
                        caseWidthString = attr.getValue();
                        break;
                    }

                if (caseLengthString == null && cpuCoolerWidth == -1)
                    return ProtocolConfiguration.CASE_NOT_COMPATIBLE;
                else if (caseWidthString == null && cpuCoolerHeight == -1)
                    return ProtocolConfiguration.CASE_NOT_COMPATIBLE;

                int caseLength = HtmlVisualizer.getDigitsFound(caseLengthString, 0);
                int caseWidth = HtmlVisualizer.getDigitsFound(caseWidthString, 1);

                if (cpuCoolerHeight > caseWidth && cpuCoolerHeight != -1)
                    return ProtocolConfiguration.CASE_NOT_COMPATIBLE;
                if (cpuCoolerWidth > caseLength && cpuCoolerWidth != -1)
                    return ProtocolConfiguration.CASE_NOT_COMPATIBLE;
                break;
            default:
                break;
        }
        return ProtocolConfiguration.COMPATIBLE;
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("cpu_socket", cpuSocket));
        attributeList.add(new Attribute("height", height));
        attributeList.add(new Attribute("water_cooled", waterCooled));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "cpu_cooler";
    }

    @Override
    public boolean canBeAddedMultipleTimes() {
        return false;
    };

}
