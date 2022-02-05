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
@Table(name = "motherboard")
public class Motherboard extends Component {
    @Id
    @Column(name = "id_motherboard", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/motherboard.webp";

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

    @SerializedName("USB 2.0 Headers")
    @Column(name = "usb_2_0_headers")
    private String usb2_0Headers;

    @SerializedName("Memory Type")
    @Column(name = "memory_type")
    private String memoryType;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Mini PCIe/MSATA Slots")
    @Column(name = "mini_pcie_msata_slots")
    private String miniPCIeMSATAslots;

    @SerializedName("M.2 Slots")
    @Column(name = "m_2_slots")
    private String m2Slots;

    @SerializedName("SLI/Crossfire")
    @Column(name = "sli_crossfire")
    private String sliCrossfire;

    @SerializedName("PCI Slots")
    @Column(name = "pci_slots")
    private String pciSlots;

    @SerializedName("Socket/CPU")
    @Column(name = "socket_cpu")
    private String socketCPU;

    @SerializedName("Chipset")
    @Column(name = "chipset")
    private String chipset;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("Wireless Networking")
    @Column(name = "wireless_networking")
    private String wirelessNetworking;

    @SerializedName("USB 3.2 Gen 1 Headers")
    @Column(name = "usb_3_2_gen_1_headers")
    private String usb3_2Gen1Headers;

    @SerializedName("USB 3.2 Gen 2x2 Headers")
    @Column(name = "usb_3_2_gen_2x2_headers")
    private String usb3_2Gen2x2Headers;

    @SerializedName("Memory Max")
    @Column(name = "memory_max")
    private String memoryMax;

    @SerializedName("Supports ECC")
    @Column(name = "supports_ecc")
    private String supportsECC;

    @SerializedName("RAID Support")
    @Column(name = "raid_support")
    private String raidSupport;

    @SerializedName("PCI-E X1 Slots")
    @Column(name = "pci_e_x1_slots")
    private String pciEX1Slots;

    @SerializedName("PCI-E X8 Slots")
    @Column(name = "pci_e_x8_slots")
    private String pciEX8Slots;

    @SerializedName("PCI-E X4 Slots")
    @Column(name = "pci_e_x4_slots")
    private String pciEX4Slots;

    @SerializedName("SATA 6 GB/s")
    @Column(name = "sata_6_gb_s")
    private String sata6GBS;

    @SerializedName("Memory Slots")
    @Column(name = "memory_slots")
    private String memorySlots;

    @SerializedName("Memory Speed")
    @Column(name = "memory_speed")
    private String memorySpeed;

    @SerializedName("M.SATA Slots")
    @Column(name = "msata_slots")
    private String msataSlots;

    @SerializedName("USB 2.0 Headers Single Port")
    @Column(name = "usb_2_0_headers_single_port")
    private String usb2_0HeadersSinglePort;

    @SerializedName("USB 3.2 Gen 2 Headers")
    @Column(name = "usb_3_2_gen_2_headers")
    private String usb3_2Gen2Headers;

    @SerializedName("Form Factor")
    @Column(name = "form_factor")
    private String formFactor;

    @SerializedName("PCI-E X16 Slots")
    @Column(name = "pci_e_x16_slots")
    private String pciEX16Slots;

    @SerializedName("Onboard Video")
    @Column(name = "onboard_video")
    private String onboardVideo;

    @SerializedName("Half Mini PCIe Slots")
    @Column(name = "half_mini_pcie_slots")
    private String halfMiniPCIeSlots;

    @SerializedName("Mini PCIe Slots")
    @Column(name = "mini_pcie_slots")
    private String miniPCIeSlots;

    @SerializedName("Onboard Ethernet")
    @Column(name = "onboard_ethernet")
    private String onboardEthernet;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "motherboard_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
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

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public Motherboard() {
    }

    public String getUsb2_0Headers() {
        return usb2_0Headers;
    }

    public void setUsb2_0Headers(String usb2_0Headers) {
        this.usb2_0Headers = usb2_0Headers;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getMiniPCIeMSATAslots() {
        return miniPCIeMSATAslots;
    }

    public void setMiniPCIeMSATAslots(String miniPCIeMSATAslots) {
        this.miniPCIeMSATAslots = miniPCIeMSATAslots;
    }

    public String getM2Slots() {
        return m2Slots;
    }

    public void setM2Slots(String m2Slots) {
        this.m2Slots = m2Slots;
    }

    public String getSliCrossfire() {
        return sliCrossfire;
    }

    public void setSliCrossfire(String sliCrossfire) {
        this.sliCrossfire = sliCrossfire;
    }

    public String getPciSlots() {
        return pciSlots;
    }

    public void setPciSlots(String pciSlots) {
        this.pciSlots = pciSlots;
    }

    public String getSocketCPU() {
        return socketCPU;
    }

    public void setSocketCPU(String socketCPU) {
        this.socketCPU = socketCPU;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
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

    public String getWirelessNetworking() {
        return wirelessNetworking;
    }

    public void setWirelessNetworking(String wirelessNetworking) {
        this.wirelessNetworking = wirelessNetworking;
    }

    public String getUsb3_2Gen1Headers() {
        return usb3_2Gen1Headers;
    }

    public void setUsb3_2Gen1Headers(String usb3_2Gen1Headers) {
        this.usb3_2Gen1Headers = usb3_2Gen1Headers;
    }

    public String getUsb3_2Gen2x2Headers() {
        return usb3_2Gen2x2Headers;
    }

    public void setUsb3_2Gen2x2Headers(String usb3_2Gen2x2Headers) {
        this.usb3_2Gen2x2Headers = usb3_2Gen2x2Headers;
    }

    public String getMemoryMax() {
        return memoryMax;
    }

    public void setMemoryMax(String memoryMax) {
        this.memoryMax = memoryMax;
    }

    public String getSupportsECC() {
        return supportsECC;
    }

    public void setSupportsECC(String supportsECC) {
        this.supportsECC = supportsECC;
    }

    public String getRaidSupport() {
        return raidSupport;
    }

    public void setRaidSupport(String raidSupport) {
        this.raidSupport = raidSupport;
    }

    public String getPciEX1Slots() {
        return pciEX1Slots;
    }

    public void setPciEX1Slots(String pciEX1Slots) {
        this.pciEX1Slots = pciEX1Slots;
    }

    public String getPciEX8Slots() {
        return pciEX8Slots;
    }

    public void setPciEX8Slots(String pciEX8Slots) {
        this.pciEX8Slots = pciEX8Slots;
    }

    public String getPciEX4Slots() {
        return pciEX4Slots;
    }

    public void setPciEX4Slots(String pciEX4Slots) {
        this.pciEX4Slots = pciEX4Slots;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSata6GBS() {
        return sata6GBS;
    }

    public void setSata6GBS(String sata6GBS) {
        this.sata6GBS = sata6GBS;
    }

    public String getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(String memorySlots) {
        this.memorySlots = memorySlots;
    }

    public String getMemorySpeed() {
        return memorySpeed;
    }

    public void setMemorySpeed(String memorySpeed) {
        this.memorySpeed = memorySpeed;
    }

    public String getMsataSlots() {
        return msataSlots;
    }

    public void setMsataSlots(String msataSlots) {
        this.msataSlots = msataSlots;
    }

    public String getUsb2_0HeadersSinglePort() {
        return usb2_0HeadersSinglePort;
    }

    public void setUsb2_0HeadersSinglePort(String usb2_0HeadersSinglePort) {
        this.usb2_0HeadersSinglePort = usb2_0HeadersSinglePort;
    }

    public String getUsb3_2Gen2Headers() {
        return usb3_2Gen2Headers;
    }

    public void setUsb3_2Gen2Headers(String usb3_2Gen2Headers) {
        this.usb3_2Gen2Headers = usb3_2Gen2Headers;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getPciEX16Slots() {
        return pciEX16Slots;
    }

    public void setPciEX16Slots(String pciEX16Slots) {
        this.pciEX16Slots = pciEX16Slots;
    }

    public String getAmazonLinkUrl() {
        return amazonLinkUrl;
    }

    public void setAmazonLinkUrl(String amazonLinkUrl) {
        this.amazonLinkUrl = amazonLinkUrl;
    }

    public String getOnboardVideo() {
        return onboardVideo;
    }

    public void setOnboardVideo(String onboardVideo) {
        this.onboardVideo = onboardVideo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHalfMiniPCIeSlots() {
        return halfMiniPCIeSlots;
    }

    public void setHalfMiniPCIeSlots(String halfMiniPCIeSlots) {
        this.halfMiniPCIeSlots = halfMiniPCIeSlots;
    }

    public String getMiniPCIeSlots() {
        return miniPCIeSlots;
    }

    public void setMiniPCIeSlots(String miniPCIeSlots) {
        this.miniPCIeSlots = miniPCIeSlots;
    }

    public String getOnboardEthernet() {
        return onboardEthernet;
    }

    public void setOnboardEthernet(String onboardEthernet) {
        this.onboardEthernet = onboardEthernet;
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Chipset", chipset));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("Form Factor", formFactor));
        attributeList.add(new Attribute("Half Mini PCIe Slots", halfMiniPCIeSlots));
        attributeList.add(new Attribute("M2 Slots", m2Slots));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Memory Max", memoryMax));
        attributeList.add(new Attribute("Memory Slots", memorySlots));
        attributeList.add(new Attribute("Memory Speed", memorySpeed));
        attributeList.add(new Attribute("Memory Type", memoryType));
        attributeList.add(new Attribute("Mini PCIe mSATA Slots", miniPCIeSlots));
        attributeList.add(new Attribute("Mini PCIe Slots", miniPCIeSlots));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("mSata Slots", msataSlots));
        attributeList.add(new Attribute("Onboard Ethernet", onboardEthernet));
        attributeList.add(new Attribute("Onboard Video", onboardVideo));
        attributeList.add(new Attribute("Part Number", partN));
        attributeList.add(new Attribute("PCIe Slots", pciSlots));
        attributeList.add(new Attribute("PCIe x1 Slots", pciEX1Slots));
        attributeList.add(new Attribute("PCIe x4 Slots", pciEX4Slots));
        attributeList.add(new Attribute("PCIe x8 Slots", pciEX8Slots));
        attributeList.add(new Attribute("PCIe x16 Slots", pciEX16Slots));
        attributeList.add(new Attribute("Raid Support", raidSupport));
        attributeList.add(new Attribute("Sata 6Gb/s Slots", sata6GBS));
        attributeList.add(new Attribute("SLI Crossfire", sliCrossfire));
        attributeList.add(new Attribute("Socket CPU", socketCPU));
        attributeList.add(new Attribute("USB 2.0 Headers", usb2_0Headers));
        attributeList.add(new Attribute("USB 2.0 Headers Single Port", usb2_0HeadersSinglePort));
        attributeList.add(new Attribute("USB 3.2 Gen 1 Headers", usb3_2Gen1Headers));
        attributeList.add(new Attribute("USB 3.2 Gen 2 Headers", usb3_2Gen2Headers));
        attributeList.add(new Attribute("USB 3.2 Gen 2x2 Headers", usb3_2Gen2x2Headers));
        attributeList.add(new Attribute("Wireless Networking", wirelessNetworking));
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
        return ComponentCategory.MOTHERBOARD.value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Motherboard [id=" + id + ", name=" + name + ", manufacturer=" + manufacturer + ", model=" + model
                + ", chipset=" + chipset + ", partN=" + partN + ", image=" + image + ", memoryMax=" + memoryMax
                + ", memorySlots=" + memorySlots + ", memoryType=" + memoryType + ", memorySpeed=" + memorySpeed
                + ", sata6GBS=" + sata6GBS + ", msataSlots=" + msataSlots + ", pciSlots=" + pciSlots + ", pciEX1Slots="
                + pciEX1Slots + ", pciEX4Slots=" + pciEX4Slots + ", pciEX8Slots=" + pciEX8Slots + ", pciEX16Slots="
                + pciEX16Slots + ", onboardEthernet=" + onboardEthernet + ", onboardVideo=" + onboardVideo
                + ", raidSupport=" + raidSupport + ", sliCrossfire=" + sliCrossfire + ", socketCPU=" + socketCPU
                + ", usb2_0Headers=" + usb2_0Headers + ", usb2_0HeadersSinglePort=" + usb2_0HeadersSinglePort
                + ", usb3_2Gen1Headers=" + usb3_2Gen1Headers + ", usb3_2Gen2Headers=" + usb3_2Gen2Headers
                + ", usb3_2Gen2x2Headers=" + usb3_2Gen2x2Headers + ", wirelessNetworking=" + wirelessNetworking
                + ", formFactor=" + formFactor + ", halfMiniPCIeSlots=" + halfMiniPCIeSlots + ", miniPCIeSlots="
                + miniPCIeSlots + ", amazonLinkUrl=" + amazonLinkUrl + "]";
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {
        boolean isCompatible = false;
        String keyword = null;
        switch (category) {
            case CASE:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "motherboard_form_factor", formFactor,
                        "\\n", false);
                if (!isCompatible)
                    return ProtocolConfiguration.CASE_NOT_COMPATIBLE;
                break;
            case CPU:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "socket",
                        socketCPU, "\\n", false);
                if (!isCompatible)
                    return ProtocolConfiguration.CPU_NOT_COMPATIBLE;
                break;
            case CPU_COOLER:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "cpu_socket", socketCPU,
                        "\\n", false);
                if (!isCompatible)
                    return ProtocolConfiguration.CPU_COOLER_NOT_COMPATIBLE;
                break;
            case EXTERNAL_STORAGE:
            case FAN:
            case FAN_CONTROLLER:
                break;
            case INTERNAL_STORAGE:
                keyword = HtmlVisualizer.getValue(allParameters, "interface");
                if (keyword == null)
                    return ProtocolConfiguration.INTERNAL_STORAGE_NOT_COMPATIBLE;
                if (keyword.contains("pcie x8"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X8;
                else if (keyword.contains("pcie x16"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X16;
                else if (keyword.contains("pcie x4"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X4;
                else if (keyword.contains("pcie x2"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X2;
                else if (keyword.contains("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else if (keyword.contains("sata"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_SATA;
                else if (keyword.contains("M.2"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_M2;
                break;
            case MEMORY:
                Integer ramSlots = Integer.parseInt(HtmlVisualizer.getValue(allParameters, "memory_slots"));
                Integer totalRam = Integer.parseInt(HtmlVisualizer.getValue(allParameters, "memory_max"));

                if (ramSlots == -1 || totalRam == -1)
                    return ProtocolConfiguration.MEMORY_NOT_COMPATIBLE;

                if (this.memoryMax == null || this.memorySlots == null)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;

                if (totalRam > convertMemoryMax() || ramSlots > Integer.parseInt(getMemorySlots()))
                    return ProtocolConfiguration.MEMORY_NOT_COMPATIBLE;

                return ProtocolConfiguration.CHECK_MOTHERBOARD_RAM_COMPATIBLE;
            case OPTICAL_DRIVE:
                return ProtocolConfiguration.CHECK_MOTHERBOARD_SATA;
            case POWER_SUPPLY:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "form_factor", formFactor, "\\n",
                        false);
                if (!isCompatible)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                break;
            case SOUND_CARD:
                keyword = HtmlVisualizer.getValue(allParameters, "interface");
                if (keyword == null)
                    return ProtocolConfiguration.SOUND_CARD_NOT_COMPATIBLE;

                if (keyword.contains("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCI;
            case VIDEO_CARD:
                isCompatible = HtmlVisualizer.containsKeywords(allParameters, "sli_crossfire",
                        sliCrossfire, "\\n", false);
                if (!isCompatible)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;
                keyword = HtmlVisualizer.getValue(allParameters, "interface").toLowerCase();
                if (keyword.contains("pcie x16"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X16;
                else if (keyword.contains("pcie x8"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X8;
                else if (keyword.contains("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else if (keyword.contains("pci"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCI;
                else if (keyword.contains("agp"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_AGP;
                break;
            case WIRED_NETWORK_ADAPTER:
            case WIRELESS_NETWORK_ADAPTER:
                keyword = HtmlVisualizer.getValue(allParameters, "interface");
                if (keyword == null) {
                    if (category == ComponentCategory.WIRED_NETWORK_ADAPTER)
                        return ProtocolConfiguration.WIRED_NETWORK_ADAPTER_NOT_COMPATIBLE;
                    else
                        return ProtocolConfiguration.WIRELESS_NETWORK_ADAPTER_NOT_COMPATIBLE;
                }

                if (keyword.contains("pcie x8"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X8;
                else if (keyword.contains("pcie x4"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X4;
                else if (keyword.contains("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else if (keyword.contains("pcie-x"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X;
                else if (keyword.contains("mini-pcie"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_MINI_PCIE;
                else if (keyword.contains("pci"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCI;
                else if (keyword.contains("USB"))
                    return ProtocolConfiguration.COMPATIBLE;
                break;
            default:
                break;
        }

        return ProtocolConfiguration.COMPATIBLE;
    }

    private Integer convertMemoryMax() {
        return HtmlVisualizer.getDigitsFound(memoryMax, 0);
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("usb_2_0_headers", usb2_0Headers));
        attributeList.add(new Attribute("memory_type", memoryType));
        // attributeList.add(new Attribute("memory_max", memoryMax));
        attributeList.add(new Attribute("memory_max", convertMemoryMax().toString()));
        attributeList.add(new Attribute("mini_pcie_slots", miniPCIeSlots));
        attributeList.add(new Attribute("m_2_slots", m2Slots));
        attributeList.add(new Attribute("sli_crossfire", sliCrossfire));
        attributeList.add(new Attribute("pci_slots", pciSlots));
        attributeList.add(new Attribute("socket_cpu", socketCPU));
        attributeList.add(new Attribute("usb_3_2_gen1_headers", usb3_2Gen1Headers));
        attributeList.add(new Attribute("usb_3_2_gen2x2_headers", usb3_2Gen2x2Headers));
        attributeList.add(new Attribute("pci_e_x1_slots", pciEX1Slots));
        attributeList.add(new Attribute("pci_e_x4_slots", pciEX4Slots));
        attributeList.add(new Attribute("pci_e_x8_slots", pciEX8Slots));
        attributeList.add(new Attribute("sata_6_gb_s", sata6GBS));
        attributeList.add(new Attribute("memory_slots", memorySlots));
        attributeList.add(new Attribute("memory_speed", memorySpeed));
        attributeList.add(new Attribute("msata_slots", msataSlots));
        attributeList.add(new Attribute("usb_3_2_gen_2_headers", usb2_0HeadersSinglePort));
        attributeList.add(new Attribute("form_factor", formFactor));
        attributeList.add(new Attribute("pci_e_x16_slots", pciEX16Slots));
        attributeList.add(new Attribute("half_mini_pcie_slots", halfMiniPCIeSlots));
        attributeList.add(new Attribute("mini_pcie_slots", miniPCIeSlots));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "motherboard";
    }

    @Override
    public boolean canBeAddedMultipleTimes() {
        return false;
    };

}
