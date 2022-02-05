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
@Table(name = "memory")
public class Memory extends Component {

    public Memory() {
    }

    @Id
    @Column(name = "id_memory", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/memory.webp";

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

    @SerializedName("Modules")
    @Column(name = "modules")
    private String modules;

    @SerializedName("CAS Latency")
    @Column(name = "cas_latency")
    private String casLatency;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Form Factor")
    @Column(name = "form_factor")
    private String formFactor;

    @SerializedName("Heat Spreader")
    @Column(name = "heat_spreader")
    private String heatSpreader;

    @SerializedName("ECC Registered")
    @Column(name = "ecc_registered")
    private String eccRegistered;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("First Word Latency")
    @Column(name = "first_word_latency")
    private String firstWordLatency;

    @SerializedName("Voltage")
    @Column(name = "voltage")
    private String voltage;

    @SerializedName("Timing")
    @Column(name = "timing")
    private String timing;

    @SerializedName("Price GB")
    @Column(name = "price_gb")
    private String priceGB;

    @SerializedName("Clock Speed")
    @Column(name = "clock_speed")
    private String speed_clock;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    @Column(name = "is_visible")
    public boolean isVisible() {
        return isVisible;
    }

    public String getSpeed_clock() {
        return speed_clock;
    }

    public void setSpeed_clock(String speed_clock) {
        this.speed_clock = speed_clock;
    }

    @JoinColumn(name = "memory_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state", nullable = false)
    private int idState;

    public State getState() {
        return state;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setState(State state) {
        this.state = state;
        this.idState = state.getId();
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getCasLatency() {
        return casLatency;
    }

    public void setCasLatency(String casLatency) {
        this.casLatency = casLatency;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getHeatSpreader() {
        return heatSpreader;
    }

    public void setHeatSpreader(String heatSpreader) {
        this.heatSpreader = heatSpreader;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEccRegistered() {
        return eccRegistered;
    }

    public void setEccRegistered(String eccRegistered) {
        this.eccRegistered = eccRegistered;
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

    public String getFirstWordLatency() {
        return firstWordLatency;
    }

    public void setFirstWordLatency(String firstWordLatency) {
        this.firstWordLatency = firstWordLatency;
    }

    public String getVoltage() {
        return voltage;
    }

    public void setVoltage(String voltage) {
        this.voltage = voltage;
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

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getPriceGB() {
        return priceGB;
    }

    public void setPriceGB(String priceGB) {
        this.priceGB = priceGB;
    }

    @Override
    public String toString() {
        return "Memory{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", amazonLinkUrl='" + amazonLinkUrl + '\'' +
                ", modules='" + modules + '\'' +
                ", casLatency='" + casLatency + '\'' +
                ", color='" + color + '\'' +
                ", formFactor='" + formFactor + '\'' +
                ", heatSpreader='" + heatSpreader + '\'' +
                ", eccRegistered='" + eccRegistered + '\'' +
                ", partN='" + partN + '\'' +
                ", firstWordLatency='" + firstWordLatency + '\'' +
                ", voltage='" + voltage + '\'' +
                ", timing='" + timing + '\'' +
                ", priceGB='" + priceGB + '\'' +
                ", speed_clock='" + speed_clock + '\'' +
                ", isVisible=" + isVisible +
                ", state=" + state +
                ", idState=" + idState +
                '}';
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
        return ComponentCategory.MEMORY.value;
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributesList = new ArrayList<>();
        attributesList.add(new Attribute("Modules", modules));
        attributesList.add(new Attribute("Cas Latency", casLatency));
        attributesList.add(new Attribute("Speed Clock", speed_clock));
        attributesList.add(new Attribute("Color", color));
        attributesList.add(new Attribute("Form Factor", formFactor));
        attributesList.add(new Attribute("Heat Spreader", heatSpreader));
        attributesList.add(new Attribute("ECC Registered", eccRegistered));
        attributesList.add(new Attribute("Part Number", partN));
        attributesList.add(new Attribute("First Word Latency", firstWordLatency));
        attributesList.add(new Attribute("Voltage", voltage));
        attributesList.add(new Attribute("manufacturer", manufacturer));
        attributesList.add(new Attribute("Model", model));
        attributesList.add(new Attribute("Timing", timing));
        attributesList.add(new Attribute("Price/GB", priceGB));
        return (attributesList);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {

        switch (category) {
            case MOTHERBOARD: {
                String memorySlots = "0", memoryMax = "0";
                int ramSlots = 0, totalRam = 0, totalRamMotherboard = 0, ramMotherboardSlots = 0;
                if (this.modules != null) {

                    for (Attribute attr : allParameters) {
                        if (attr.name.equalsIgnoreCase("memory_slots"))
                            memorySlots = attr.value;
                        else if (attr.name.equalsIgnoreCase("memory_max"))
                            memoryMax = attr.value;
                    }
                    // Motherboard variable
                    ramMotherboardSlots = Integer.parseInt(memorySlots);
                    totalRamMotherboard = Integer.parseInt(memoryMax.replace(" GB", ""));
                    // Memory variable
                    // String[] gigabyte = modules.replace("GB", "").split(" x ");
                    // totalRam = Integer.parseInt(gigabyte[0]) * Integer.parseInt(gigabyte[1]);
                    // ramSlots = Integer.parseInt(gigabyte[0]);
                    totalRam = getTotalRam();
                    ramSlots = getMemorySlots();
                    if (totalRam == -1 || ramSlots == -1)
                        return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                }

                String memoryType = speed_clock.substring(0, speed_clock.indexOf("-"));

                boolean isCompatible = HtmlVisualizer.containsKeywords(allParameters, "memory_type", memoryType, "\\n",
                        false); // Memory Type (e.g. DDR4)
                if (!isCompatible)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                if (modules != null) {
                    System.out.println("RAM Slots: " + ramSlots + " Motherboard Slots: " + ramMotherboardSlots);
                    System.out.println("Total RAM: " + totalRam + " Motherboard RAM: " + totalRamMotherboard);
                    if (ramSlots > ramMotherboardSlots || totalRam > totalRamMotherboard) // Partial Check
                        return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_RAM_COMPATIBLE;
                }

                break;
            }
            default:
                break;
        }

        return ProtocolConfiguration.COMPATIBLE;

    }

    public Integer getMemorySlots() {
        if (this.modules == null || this.modules.isEmpty())
            return -1;
        String[] gigabyte = modules.replace("GB", "").split(" x ");

            try {
                return Integer.parseInt(gigabyte[0]);
            } catch (NumberFormatException e) {
                return -1;
            }
    }

    public Integer getTotalRam() {
        if (this.modules == null || this.modules.isEmpty())
            return -1;
        String[] gigabyte = modules.replace("GB", "").split(" x ");

        try {
                return Integer.parseInt(gigabyte[0])* Integer.parseInt(gigabyte[1]);
            } catch (NumberFormatException e) {
                return -1;
            }

    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("modules", modules));
        attributeList.add(new Attribute("clock_speed", speed_clock));
        attributeList.add(new Attribute("memory_slots", getMemorySlots().toString()));
        attributeList.add(new Attribute("memory_max", getTotalRam().toString()));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "memory";
    }

}
