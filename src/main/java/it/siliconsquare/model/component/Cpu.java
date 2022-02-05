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
@Table(name = "cpu")
public class Cpu extends Component {

    @Id
    @Column(name = "id_cpu", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/cpu.webp";

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

    @SerializedName("Efficiency L2 Cache")
    @Column(name = "efficiency_l2_cache")
    private String efficiencyL2Cache;

    @SerializedName("Microarchitecture")
    @Column(name = "microarchitecture")
    private String microarchitecture;

    @SerializedName("Integrated Graphics")
    @Column(name = "integrated_graphics")
    private String integratedGraphics;

    @SerializedName("Performance L1 Cache")
    @Column(name = "performance_l1_cache")
    private String performanceL1Cache;

    @SerializedName("Performance Core Clock")
    @Column(name = "performance_core_clock")
    private String performanceCoreClock;

    @SerializedName("ECC Support")
    @Column(name = "ecc_support")
    private String eCCSupport;

    @SerializedName("TDP")
    @Column(name = "tdp")
    private String tdp;

    @SerializedName("Lithography")
    @Column(name = "lithography")
    private String lithography;

    @SerializedName("Efficiency L1 Cache")
    @Column(name = "efficiency_l1_cache")
    private String efficiencyL1Cache;

    @SerializedName("Performance Boost Clock")
    @Column(name = "performance_boost_clock")
    private String performanceBoostClock;

    @SerializedName("Maximum Supported Memory")
    @Column(name = "maximum_supported_memory")
    private String maximumSupportedMemory;

    @SerializedName("Core Count")
    @Column(name = "core_count")
    private String coreCount;

    @SerializedName("Series")
    @Column(name = "series")
    private String series;

    @SerializedName("Performance L2 Cache")
    @Column(name = "performance_l2_cache")
    private String performanceL2Cache;

    @SerializedName("Simultaneous Multithreading")
    @Column(name = "simultaneous_multithreading")
    private String simultaneousMultithreading;

    @SerializedName("Part N")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Core Family")
    @Column(name = "core_family")
    private String coreFamily;

    @SerializedName("Packaging")
    @Column(name = "packaging")
    private String packaging;

    @SerializedName("Includes CPU Cooler")
    @Column(name = "includes_cpu_cooler")
    private String includesCPUCooler;

    @SerializedName("L3 Cache")
    @Column(name = "l3_cache")
    private String l3Cache;

    @SerializedName("Socket")
    @Column(name = "socket")
    private String socket;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "cpu_state")
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

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Cpu() {
    }

    public String getEfficiencyL2Cache() {
        return efficiencyL2Cache;
    }

    public void setEfficiencyL2Cache(String efficiencyL2Cache) {
        this.efficiencyL2Cache = efficiencyL2Cache;
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

    public String getMicroarchitecture() {
        return microarchitecture;
    }

    public void setMicroarchitecture(String microarchitecture) {
        this.microarchitecture = microarchitecture;
    }

    public String getIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(String integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
    }

    public String getPerformanceL1Cache() {
        return performanceL1Cache;
    }

    public void setPerformanceL1Cache(String performanceL1Cache) {
        this.performanceL1Cache = performanceL1Cache;
    }

    public String getPerformanceCoreClock() {
        return performanceCoreClock;
    }

    public void setPerformanceCoreClock(String performanceCoreClock) {
        this.performanceCoreClock = performanceCoreClock;
    }

    public String geteCCSupport() {
        return eCCSupport;
    }

    public void seteCCSupport(String eCCSupport) {
        this.eCCSupport = eCCSupport;
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

    public String getTdp() {
        return tdp;
    }

    public void setTdp(String tdp) {
        this.tdp = tdp;
    }

    public String getLithography() {
        return lithography;
    }

    public void setLithography(String lithography) {
        this.lithography = lithography;
    }

    public String getEfficiencyL1Cache() {
        return efficiencyL1Cache;
    }

    public void setEfficiencyL1Cache(String efficiencyL1Cache) {
        this.efficiencyL1Cache = efficiencyL1Cache;
    }

    public String getPerformanceBoostClock() {
        return performanceBoostClock;
    }

    public void setPerformanceBoostClock(String performanceBoostClock) {
        this.performanceBoostClock = performanceBoostClock;
    }

    public String getMaximumSupportedMemory() {
        return maximumSupportedMemory;
    }

    public void setMaximumSupportedMemory(String maximumSupportedMemory) {
        this.maximumSupportedMemory = maximumSupportedMemory;
    }

    public String getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(String coreCount) {
        this.coreCount = coreCount;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getPerformanceL2Cache() {
        return performanceL2Cache;
    }

    public void setPerformanceL2Cache(String performanceL2Cache) {
        this.performanceL2Cache = performanceL2Cache;
    }

    public String getSimultaneousMultithreading() {
        return simultaneousMultithreading;
    }

    public void setSimultaneousMultithreading(String simultaneousMultithreading) {
        this.simultaneousMultithreading = simultaneousMultithreading;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getCoreFamily() {
        return coreFamily;
    }

    public void setCoreFamily(String coreFamily) {
        this.coreFamily = coreFamily;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIncludesCPUCooler() {
        return includesCPUCooler;
    }

    public void setIncludesCPUCooler(String includesCPUCooler) {
        this.includesCPUCooler = includesCPUCooler;
    }

    public String getL3Cache() {
        return l3Cache;
    }

    public void setL3Cache(String l3Cache) {
        this.l3Cache = l3Cache;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    @Override
    public String toString() {
        return "CPU{" + "idCPU=" + id + ", efficiencyL2Cache='" + efficiencyL2Cache + '\'' + ", image='" + image + '\''
                + ", name='" + name + '\'' + ", microarchitecture='" + microarchitecture + '\''
                + ", integratedGraphics='" + integratedGraphics + '\'' + ", performanceL1Cache='" + performanceL1Cache
                + '\'' + ", performanceCoreClock='" + performanceCoreClock + '\'' + ", eCCSupport='" + eCCSupport + '\''
                + ", amazonLinkUrl='" + amazonLinkUrl + '\'' + ", manufacturer='" + manufacturer + '\'' + ", tdp='"
                + tdp + '\'' + ", lithography='" + lithography + '\'' + ", efficiencyL1Cache='" + efficiencyL1Cache
                + '\'' + ", performanceBoostClock='" + performanceBoostClock + '\'' + ", maximumSupportedMemory='"
                + maximumSupportedMemory + '\'' + ", coreCount='" + coreCount + '\'' + ", series='" + series + '\''
                + ", performanceL2Cache='" + performanceL2Cache + '\'' + ", simultaneousMultithreading='"
                + simultaneousMultithreading + '\'' + ", partN='" + partN + '\'' + ", coreFamily='" + coreFamily + '\''
                + ", packaging='" + packaging + '\'' + ", model='" + model + '\'' + ", includesCPUCooler='"
                + includesCPUCooler + '\'' + ", l3Cache='" + l3Cache + '\'' + ", socket='" + socket + '\'' + '}';
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
        return ComponentCategory.CPU.value;
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Socket", socket));
        attributeList.add(new Attribute("Core Count", coreCount));
        attributeList.add(new Attribute("Core Family", coreFamily));
        attributeList.add(new Attribute("Lithography", lithography));
        attributeList.add(new Attribute("TDP", tdp));
        attributeList.add(new Attribute("Efficiency L1 Cache", efficiencyL1Cache));
        attributeList.add(new Attribute("Performance L1 Cache", performanceL1Cache));
        attributeList.add(new Attribute("Performance L2 Cache", performanceL2Cache));
        attributeList.add(new Attribute("Performance Boost Clock", performanceBoostClock));
        attributeList.add(new Attribute("Maximum Supported Memory", maximumSupportedMemory));
        attributeList.add(new Attribute("Simultaneous Multithreading", simultaneousMultithreading));
        attributeList.add(new Attribute("ECC Support", eCCSupport));
        attributeList.add(new Attribute("Efficiency L2 Cache", efficiencyL2Cache));
        attributeList.add(new Attribute("Performance Core Clock", performanceCoreClock));
        attributeList.add(new Attribute("Integrated Graphics", integratedGraphics));
        attributeList.add(new Attribute("Packaging", packaging));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("Includes CPU Cooler", includesCPUCooler));
        attributeList.add(new Attribute("L3 Cache", l3Cache));
        attributeList.add(new Attribute("Microarchitecture", microarchitecture));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Series", series));
        attributeList.add(new Attribute("Part Number", partN));
        return attributeList;
        // return super.toTableHTML(attributeList);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {

        boolean isCompatible = false;
        switch (category) {
            case MOTHERBOARD:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "socket_cpu",
                        socket, "\\n", false);
                if (!isCompatible)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                break;
            case CPU_COOLER:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "cpu_socket",
                        socket, "\\n", false);
                if (!isCompatible)
                    return ProtocolConfiguration.CPU_COOLER_NOT_COMPATIBLE;
                break;
            default:
                break;
        }
        return ProtocolConfiguration.COMPATIBLE;

    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("socket", socket));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "cpu";
    }

    @Override
    public boolean canBeAddedMultipleTimes() {
        return false;
    };

}