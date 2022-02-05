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
@Table(name = "video_card")
public class VideoCard extends Component {

    @Id
    @Column(name = "id_video_card", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https:// resources.siliconsquare.it/assets/images/component/video_card.webp";

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

    @SerializedName("Boost Clock")
    @Column(name = "boost_clock")
    private String boostClock;

    @SerializedName("Chipset")
    @Column(name = "chipset")
    private String chipset;

    @SerializedName("Chipset Boost Clock")
    @Column(name = "expansion_slot_width")
    private String expansionSlotWidth;

    @SerializedName("DVI-D Dual-Link")
    @Column(name = "dvi_d_dual_link")
    private String dviDDualLink;

    @SerializedName("Memory")
    @Column(name = "memory")
    private String memory;

    @SerializedName("DVI-I Dual-Link")
    @Column(name = "dvi_i_dual_link")
    private String dviIDualLink;

    @SerializedName("Frame Sync")
    @Column(name = "frame_sync")
    private String frameSync;

    @SerializedName("Virtual Link")
    @Column(name = "virtual_link")
    private String virtualLink;

    @SerializedName("Core Clock")
    @Column(name = "core_clock")
    private String coreClock;

    @SerializedName("Mini-DisplayPort Ports")
    @Column(name = "mini_displayport_ports")
    private String miniDisplayPortPorts;

    @SerializedName("Memory Type")
    @Column(name = "memory_type")
    private String memoryType;

    @SerializedName("Effective Memory Clock")
    @Column(name = "effective_memory_clock")
    private String effectiveMemoryClock;

    @SerializedName("Display Port")
    @Column(name = "display_port")
    private String displayPort;

    @SerializedName("Sli Crossfire")
    @Column(name = "sli_crossfire")
    private String sliCrossFire;

    @SerializedName("DVI Ports")
    @Column(name = "dvi_ports")
    private String dviPorts;

    @SerializedName("Display Port Ports")
    @Column(name = "displayport_ports")
    private String displayPortPorts;

    @SerializedName("TDP")
    @Column(name = "tdp")
    private String tdp;

    @SerializedName("Mini HDMI Ports")
    @Column(name = "mini_hdmi_ports")
    private String miniHdmiPorts;

    @SerializedName("Cooling")
    @Column(name = "cooling")
    private String cooling;

    @SerializedName("External Power")
    @Column(name = "external_power")
    private String externalPower;

    @SerializedName("HDMI Ports")
    @Column(name = "hdmi_ports")
    private String hdmiPorts;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("VGA")
    @Column(name = "vga")
    private String vga;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Length")
    @Column(name = "length")
    private String length;

    @SerializedName("HDMI")
    @Column(name = "hdmi")
    private String hdmi;

    @SerializedName("Interface")
    @Column(name = "interface")
    private String _interface;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "video_card_state")
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

    public VideoCard() {
    }

    public String getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(String boostClock) {
        this.boostClock = boostClock;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public String getExpansionSlotWidth() {
        return expansionSlotWidth;
    }

    public void setExpansionSlotWidth(String expansionSlotWidth) {
        this.expansionSlotWidth = expansionSlotWidth;
    }

    public String getDviDDualLink() {
        return dviDDualLink;
    }

    public void setDviDDualLink(String dviDDualLink) {
        this.dviDDualLink = dviDDualLink;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getDviIDualLink() {
        return dviIDualLink;
    }

    public void setDviIDualLink(String dviIDualLink) {
        this.dviIDualLink = dviIDualLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFrameSync() {
        return frameSync;
    }

    public void setFrameSync(String frameSync) {
        this.frameSync = frameSync;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVirtualLink() {
        return virtualLink;
    }

    public void setVirtualLink(String virtualLink) {
        this.virtualLink = virtualLink;
    }

    public String getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(String coreClock) {
        this.coreClock = coreClock;
    }

    public String getMiniDisplayPortPorts() {
        return miniDisplayPortPorts;
    }

    public void setMiniDisplayPortPorts(String miniDisplayPortPorts) {
        this.miniDisplayPortPorts = miniDisplayPortPorts;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public String getEffectiveMemoryClock() {
        return effectiveMemoryClock;
    }

    public void setEffectiveMemoryClock(String effectiveMemoryClock) {
        this.effectiveMemoryClock = effectiveMemoryClock;
    }

    public String getDisplayPort() {
        return displayPort;
    }

    public void setDisplayPort(String displayPort) {
        this.displayPort = displayPort;
    }

    public String getSliCrossFire() {
        return sliCrossFire;
    }

    public void setSliCrossFire(String sliCrossFire) {
        this.sliCrossFire = sliCrossFire;
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

    public String getDviPorts() {
        return dviPorts;
    }

    public void setDviPorts(String dviPorts) {
        this.dviPorts = dviPorts;
    }

    public String getDisplayPortPorts() {
        return displayPortPorts;
    }

    public void setDisplayPortPorts(String displayPortPorts) {
        this.displayPortPorts = displayPortPorts;
    }

    public String getTdp() {
        return tdp;
    }

    public void setTdp(String tdp) {
        this.tdp = tdp;
    }

    public String getMiniHdmiPorts() {
        return miniHdmiPorts;
    }

    public void setMiniHdmiPorts(String miniHdmiPorts) {
        this.miniHdmiPorts = miniHdmiPorts;
    }

    public String getCooling() {
        return cooling;
    }

    public void setCooling(String cooling) {
        this.cooling = cooling;
    }

    public String getExternalPower() {
        return externalPower;
    }

    public void setExternalPower(String externalPower) {
        this.externalPower = externalPower;
    }

    public String getHdmiPorts() {
        return hdmiPorts;
    }

    public void setHdmiPorts(String hdmiPorts) {
        this.hdmiPorts = hdmiPorts;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getVga() {
        return vga;
    }

    public void setVga(String vga) {
        this.vga = vga;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getHdmi() {
        return hdmi;
    }

    public void setHdmi(String hdmi) {
        this.hdmi = hdmi;
    }

    public String getInterface() {
        return _interface;
    }

    public void setInterface(String _interface) {
        this._interface = _interface;
    }

    @Override
    public String toString() {
        return "VideoCard{" + "idVideoCard=" + id + ", boostClock='" + boostClock + '\'' + ", chipset='" + chipset
                + '\'' + ", expansionSlotWidth='" + expansionSlotWidth + '\'' + ", dviDDualLink='" + dviDDualLink + '\''
                + ", memory='" + memory + '\'' + ", dviIDualLink='" + dviIDualLink + '\'' + ", image=" + image
                + ", frameSync='" + frameSync + '\'' + ", name='" + name + '\'' + ", virtualLink='" + virtualLink + '\''
                + ", coreClock='" + coreClock + '\'' + ", miniDisplayPortPorts='" + miniDisplayPortPorts + '\''
                + ", memoryType='" + memoryType + '\'' + ", effectiveMemoryClock='" + effectiveMemoryClock + '\''
                + ", displayPort='" + displayPort + '\'' + ", sliCrossFire='" + sliCrossFire + '\'' + ", amazonLinkUrl="
                + amazonLinkUrl + ", manufacturer='" + manufacturer + '\'' + ", dviPorts='" + dviPorts + '\''
                + ", displayPortPorts='" + displayPortPorts + '\'' + ", tdp='" + tdp + '\'' + ", miniHdmiPorts='"
                + miniHdmiPorts + '\'' + ", cooling='" + cooling + '\'' + ", externalPower='" + externalPower + '\''
                + ", hdmiPorts='" + hdmiPorts + '\'' + ", color='" + color + '\'' + ", vga='" + vga + '\'' + ", partN='"
                + partN + '\'' + ", length='" + length + '\'' + ", model='" + model + '\'' + ", hdmi='" + hdmi + '\''
                + ", _interface='" + _interface + '\'' + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<Attribute>();
        attributeList.add(new Attribute("Boost Clock", boostClock));
        attributeList.add(new Attribute("Chipset", chipset));
        attributeList.add(new Attribute("Expansion Slot Width", expansionSlotWidth));
        attributeList.add(new Attribute("DVI-D Dual Link", dviDDualLink));
        attributeList.add(new Attribute("Memory", memory));
        attributeList.add(new Attribute("DVI-I Dual Link", dviIDualLink));
        attributeList.add(new Attribute("Frame Sync", frameSync));
        attributeList.add(new Attribute("Virtual Link", virtualLink));
        attributeList.add(new Attribute("Core Clock", coreClock));
        attributeList.add(new Attribute("Mini Display Port Ports", miniDisplayPortPorts));
        attributeList.add(new Attribute("Memory Type", memoryType));
        attributeList.add(new Attribute("Effective Memory Clock", effectiveMemoryClock));
        attributeList.add(new Attribute("Display Port", displayPort));
        attributeList.add(new Attribute("SLI Cross Fire", sliCrossFire));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("DVI Ports", dviPorts));
        attributeList.add(new Attribute("Display Port Ports", displayPortPorts));
        attributeList.add(new Attribute("TDP", tdp));
        attributeList.add(new Attribute("Mini HDMI Ports", miniHdmiPorts));
        attributeList.add(new Attribute("Cooling", cooling));
        attributeList.add(new Attribute("External Power", externalPower));
        attributeList.add(new Attribute("HDMI Ports", hdmiPorts));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("VGA", vga));
        attributeList.add(new Attribute("Part N", partN));
        attributeList.add(new Attribute("Length", length));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("HDMI", hdmi));
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
        return ComponentCategory.VIDEO_CARD.value;
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
            case MOTHERBOARD: {
                boolean sliCrossFireCheck = HtmlVisualizer.containsKeywords(allParameters, "sli_crossfire",
                        sliCrossFire, "\\n",
                        false);
                // TODO check for compatibility out of the method
                if (!sliCrossFireCheck)
                    return ProtocolConfiguration.MOTHERBOARD_NOT_COMPATIBLE;

                if (_interface.toLowerCase().contains("pcie x16"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X16;
                else if (_interface.toLowerCase().contains("pcie x8"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X8;
                else if (_interface.toLowerCase().contains("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else if (_interface.toLowerCase().contains("pci"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCI;
                else if (_interface.toLowerCase().contains("agp"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_AGP;
                break;
            }

            case CASE: {
                for (Attribute s : allParameters) {
                    if (!s.name.contains("maximum_video_card_length"))
                        continue;
                    int lengthOfVideoCard = HtmlVisualizer.getDigitsFound(length, 0);
                    int maxVideoCardLengthOfCase = HtmlVisualizer.getDigitsFound(s.getValue(), 0);
                    if (lengthOfVideoCard == -1 || maxVideoCardLengthOfCase == -1)
                        continue;
                    if (lengthOfVideoCard > maxVideoCardLengthOfCase) {
                        return ProtocolConfiguration.CASE_NOT_COMPATIBLE;
                    }
                }
                break;
            }
            default:
                break;
        }

        return ProtocolConfiguration.COMPATIBLE;

    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("interface", _interface));
        attributeList.add(new Attribute("length", length));
        attributeList.add(new Attribute("sli_crossfire", sliCrossFire));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "video_card";
    }

    @Override
    public boolean canBeAddedMultipleTimes() {
        return false;
    };

}
