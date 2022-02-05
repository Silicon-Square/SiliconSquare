package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "internal_storage")
public class InternalStorage extends Component {

    @Id
    @Column(name = "id_internal_storage", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image", nullable = false)
    private String image = "https://resources.siliconsquare.it/assets/images/component/internal_storage.webp";

    @SerializedName("Name")
    @Column(name = "name", nullable = false)
    private String name;

    @SerializedName("Model")
    @Column(name = "model", nullable = false)
    private String model;

    @SerializedName("Manufacturer")
    @Column(name = "manufacturer", nullable = false)
    private String manufacturer;

    @SerializedName("Amazon Link")
    @Column(name = "amazon_link_url", nullable = false)
    private String amazonLinkUrl;

    @SerializedName("Price GB")
    @Column(name = "price_gb", nullable = false)
    private String priceGB;

    @SerializedName("Interface")
    @Column(name = "interface", nullable = false)
    private String _interface;

    @SerializedName("Part Number")
    @Column(name = "part_n", nullable = false)
    private String partN;

    @SerializedName("Capacity")
    @Column(name = "capacity", nullable = false)
    private String capacity;

    @SerializedName("Hybrid SSD Cache")
    @Column(name = "hybrid_ssd_cache", nullable = false)
    private String hybridSsdCache;

    @SerializedName("Cache")
    @Column(name = "cache", nullable = false)
    private String cache;

    @SerializedName("Form Factor")
    @Column(name = "form_factor", nullable = false)
    private String formFactor;

    @SerializedName("SSD Controller")
    @Column(name = "ssd_controller", nullable = false)
    private String ssdController;

    @SerializedName("NVMe")
    @Column(name = "nvme", nullable = false)
    private String nvme;

    @SerializedName("Type")
    @Column(name = "type", nullable = false)
    private String type;

    @SerializedName("SSD/NAND Flash Type")
    @Column(name = "ssd_nand_flash_type")
    private String ssdNandFlashType;

    @Column(name = "is_visible", nullable = false)
    private boolean isVisible = true;

    @JoinColumn(name = "internal_storage_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

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

    public String getHybridSsdCache() {
        return hybridSsdCache;
    }

    public void setHybridSsdCache(String hybridSsdCache) {
        this.hybridSsdCache = hybridSsdCache;
    }

    public String getCache() {
        return cache;
    }

    public void setCache(String cache) {
        this.cache = cache;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getSsdController() {
        return ssdController;
    }

    public void setSsdController(String ssdController) {
        this.ssdController = ssdController;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNvme() {
        return nvme;
    }

    public void setNvme(String nvme) {
        this.nvme = nvme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSsdNandFlashType() {
        return ssdNandFlashType;
    }

    public void setSsdNandFlashType(String ssdNandFlashType) {
        this.ssdNandFlashType = ssdNandFlashType;
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
        return "InternalStorage{" + "id=" + id + ", image='" + image + '\'' + ", priceGB='" + priceGB + '\''
                + ", _interface='" + _interface + '\'' + ", partN='" + partN + '\'' + ", capacity='" + capacity + '\''
                + ", hybridSsdCache='" + hybridSsdCache + '\'' + ", cache='" + cache + '\'' + ", formFactor='"
                + formFactor + '\'' + ", ssdController='" + ssdController + '\'' + ", amazonLinkUrl='" + amazonLinkUrl
                + '\'' + ", manufacturer='" + manufacturer + '\'' + ", name='" + name + '\'' + ", nvme='" + nvme + '\''
                + ", type='" + type + '\'' + ", ssdNandFlashType='" + ssdNandFlashType + '\'' + ", model='" + model
                + '\'' + ", isVisible=" + isVisible + ", state=" + state + '}';
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
        return ComponentCategory.INTERNAL_STORAGE.value;
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
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Price", priceGB));
        attributeList.add(new Attribute("Interface", _interface));
        attributeList.add(new Attribute("Part Number", partN));
        attributeList.add(new Attribute("Capacity", capacity));
        attributeList.add(new Attribute("Hybrid SSD Cache", hybridSsdCache));
        attributeList.add(new Attribute("Cache", cache));
        attributeList.add(new Attribute("Form Factor", formFactor));
        attributeList.add(new Attribute("SSD Controller", ssdController));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("NVME", nvme));
        attributeList.add(new Attribute("Type", type));
        attributeList.add(new Attribute("SSD/NAND Flash Type", ssdNandFlashType));
        attributeList.add(new Attribute("Model", model));
        return attributeList;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {
        switch (category) {
            case MOTHERBOARD:
                if (_interface.contains("sata"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_SATA;
                else if (_interface.contains("m.2"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_M2;
                else if (_interface.contains("pcie x2"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X2;
                else if (_interface.contains("pcie x4"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X4;
                else if (_interface.contains("pcie x8"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X8;
                else if (_interface.equalsIgnoreCase("pcie x1"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X1;
                else if (_interface.contains("pcie x16"))
                    return ProtocolConfiguration.CHECK_MOTHERBOARD_PCIE_X16;
            default:
                break;

            // TODO: verify if the keywords are extrapolated correctly (ugnorecase,
            // contains) // sata_6gb_s

            // check if a sata, m.2, pcie x16, pcie x4, pcie x8, pcie x1 port is available
            // into the
            // motherboard

            // _interface = 'M.2 (M)','SATA 6 Gb/s','M.2 (B+M)','SATA 3 Gb/s','PCIe
            // x16','PCIe x4','PCIe x8','PCIe x1'
        }
        return ProtocolConfiguration.COMPATIBLE; // TODO Configuration.checkPorts();
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("interface", _interface));
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "internal_storage";
    }

}
