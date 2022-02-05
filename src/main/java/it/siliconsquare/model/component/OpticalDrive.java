package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "optical_drive")
public class OpticalDrive extends Component {

    @Id
    @Column(name = "id_optical_drive", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/optical_drive.webp";

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

    @SerializedName("Buffer Cache")
    @Column(name = "buffer_cache")
    private String bufferCache;

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("DVD-R Speed")
    @Column(name = "dvd_r_speed")
    private String dvdRSpeed;

    @SerializedName("BD-R Dual Layer Speed")
    @Column(name = "bd_r_dual_layer_speed")
    private String bdRDualLayerSpeed;

    @SerializedName("BD-R Speed")
    @Column(name = "bd_r_speed")
    private String bdRSpeed;

    @SerializedName("Color")
    @Column(name = "color")
    private String color;

    @SerializedName("BD-RE Speed")
    @Column(name = "bd_re_speed")
    private String bdRESpeed;

    @SerializedName("DVD-RAM Speed")
    @Column(name = "dvd_ram_speed")
    private String dvdRAMSpeed;

    @SerializedName("CD-RW Speed")
    @Column(name = "cd_rw_speed")
    private String cdRwSpeed;

    @SerializedName("Interface")
    @Column(name = "interface")
    private String _interface;

    @SerializedName("DVD-ROM Speed")
    @Column(name = "dvd_rom_speed")
    private String dvdROMSpeed;

    @SerializedName("BD-RE Dual Layer Speed")
    @Column(name = "bd_re_dual_layer_speed")
    private String bdREDualLayerSpeed;

    @SerializedName("DVD-RW Speed")
    @Column(name = "dvd_rw_speed")
    private String dvdRWSpeed;

    @SerializedName("Form Factor")
    @Column(name = "form_factor")
    private String formFactor;

    @SerializedName("DVD+R Speed")
    @Column(name = "dvd_plus_r_speed")
    private String dvdRPlusSpeed;

    @SerializedName("DVD+R Dual Layer Speed")
    @Column(name = "dvd_plus_r_dual_layer_speed")
    private String dvdRPlusDualLayerSpeed;

    @Column(name = "cd_rom_speed")
    @SerializedName("CD-ROM Speed")
    private String cdROMSpeed;

    @SerializedName("DVD + RW Speed")
    @Column(name = "dvd_plus_rw_speed")
    private String dvdRPlusRWSpeed;

    @SerializedName("BD ROM Speed")
    @Column(name = "bd_rom_speed")
    private String bdROMSpeed;

    @SerializedName("CD-R Speed")
    @Column(name = "cd_r_speed")
    private String cdRSpeed;

    @SerializedName("DVD-R Dual Layer Speed")
    @Column(name = "dvd_r_dual_layer_speed")
    private String dvdRDualLayerSpeed;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "optical_drive_state")
    @ManyToOne
    private State state;

    @Column(name = "id_state")
    private int idState;

    public OpticalDrive() {
    }

    public String getBufferCache() {
        return bufferCache;
    }

    public void setBufferCache(String bufferCache) {
        this.bufferCache = bufferCache;
    }

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getDvdRSpeed() {
        return dvdRSpeed;
    }

    public void setDvdRSpeed(String dvdRSpeed) {
        this.dvdRSpeed = dvdRSpeed;
    }

    public String getBdRDualLayerSpeed() {
        return bdRDualLayerSpeed;
    }

    public void setBdRDualLayerSpeed(String bdRDualLayerSpeed) {
        this.bdRDualLayerSpeed = bdRDualLayerSpeed;
    }

    public String getBdRSpeed() {
        return bdRSpeed;
    }

    public void setBdRSpeed(String bdRSpeed) {
        this.bdRSpeed = bdRSpeed;
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

    public String getBdRESpeed() {
        return bdRESpeed;
    }

    public void setBdRESpeed(String bdRESpeed) {
        this.bdRESpeed = bdRESpeed;
    }

    public String getDvdRAMSpeed() {
        return dvdRAMSpeed;
    }

    public void setDvdRAMSpeed(String dvdRAMSpeed) {
        this.dvdRAMSpeed = dvdRAMSpeed;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCdRwSpeed() {
        return cdRwSpeed;
    }

    public void setCdRwSpeed(String cdRwSpeed) {
        this.cdRwSpeed = cdRwSpeed;
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

    public String getDvdROMSpeed() {
        return dvdROMSpeed;
    }

    public void setDvdROMSpeed(String dvdROMSpeed) {
        this.dvdROMSpeed = dvdROMSpeed;
    }

    public String getBdREDualLayerSpeed() {
        return bdREDualLayerSpeed;
    }

    public void setBdREDualLayerSpeed(String bdREDualLayerSpeed) {
        this.bdREDualLayerSpeed = bdREDualLayerSpeed;
    }

    public String getDvdRWSpeed() {
        return dvdRWSpeed;
    }

    public void setDvdRWSpeed(String dvdRWSpeed) {
        this.dvdRWSpeed = dvdRWSpeed;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getDvdRPlusSpeed() {
        return dvdRPlusSpeed;
    }

    public void setDvdRPlusSpeed(String dvdRPlusSpeed) {
        this.dvdRPlusSpeed = dvdRPlusSpeed;
    }

    public String getAmazonLinkUrl() {
        return amazonLinkUrl;
    }

    public void setAmazonLinkUrl(String amazonLinkUrl) {
        this.amazonLinkUrl = amazonLinkUrl;
    }

    public String getDvdRPlusDualLayerSpeed() {
        return dvdRPlusDualLayerSpeed;
    }

    public void setDvdRPlusDualLayerSpeed(String dvdRPlusDualLayerSpeed) {
        this.dvdRPlusDualLayerSpeed = dvdRPlusDualLayerSpeed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCdROMSpeed() {
        return cdROMSpeed;
    }

    public void setCdROMSpeed(String cdROMSpeed) {
        this.cdROMSpeed = cdROMSpeed;
    }

    public String getDvdRPlusRWSpeed() {
        return dvdRPlusRWSpeed;
    }

    public void setDvdRPlusRWSpeed(String dvdRPlusRWSpeed) {
        this.dvdRPlusRWSpeed = dvdRPlusRWSpeed;
    }

    public String getBdROMSpeed() {
        return bdROMSpeed;
    }

    public void setBdROMSpeed(String bdROMSpeed) {
        this.bdROMSpeed = bdROMSpeed;
    }

    public String getCdRSpeed() {
        return cdRSpeed;
    }

    public void setCdRSpeed(String cdRSpeed) {
        this.cdRSpeed = cdRSpeed;
    }

    public String getDvdRDualLayerSpeed() {
        return dvdRDualLayerSpeed;
    }

    public void setDvdRDualLayerSpeed(String dvdRDualLayerSpeed) {
        this.dvdRDualLayerSpeed = dvdRDualLayerSpeed;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

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
        return "OpticalDrive{" + "idOpticalDrive=" + id + ", bufferCache=" + bufferCache + ", partN=" + partN
                + ", dvdRSpeed=" + dvdRSpeed + ", bdRDualLayerSpeed=" + bdRDualLayerSpeed + ", bdRSpeed=" + bdRSpeed
                + ", manufacturer='" + manufacturer + '\'' + ", color='" + color + '\'' + ", bdRESpeed=" + bdRESpeed
                + ", dvdRAMSpeed=" + dvdRAMSpeed + ", model='" + model + '\'' + ", cdRwSpeed=" + cdRwSpeed + ", image='"
                + image + '\'' + ", _interface='" + _interface + '\'' + ", dvdROMSpeed=" + dvdROMSpeed
                + ", bdREDualLayerSpeed=" + bdREDualLayerSpeed + ", dvdRWSpeed=" + dvdRWSpeed + ", formFactor='"
                + formFactor + '\'' + ", dvdRPlusSpeed=" + dvdRPlusSpeed + ", amazonLinkUrl='" + amazonLinkUrl + '\''
                + ", dvdRPlusDualLayerSpeed=" + dvdRPlusDualLayerSpeed + ", name='" + name + '\'' + ", cdROMSpeed="
                + cdROMSpeed + ", dvdRPlusRWSpeed=" + dvdRPlusRWSpeed + ", bdROMSpeed=" + bdROMSpeed + ", cdRSpeed="
                + cdRSpeed + ", dvdRDualLayerSpeed=" + dvdRDualLayerSpeed + ", isVisible=" + isVisible + ", state="
                + state + '}';
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Buffer Cache", bufferCache));
        attributeList.add(new Attribute("Part N", partN));
        attributeList.add(new Attribute("DVD-R Speed", dvdRSpeed));
        attributeList.add(new Attribute("BD-R Dual Layer Speed", bdRDualLayerSpeed));
        attributeList.add(new Attribute("BD-R Speed", bdRSpeed));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Color", color));
        attributeList.add(new Attribute("BD-RE Speed", bdRESpeed));
        attributeList.add(new Attribute("DVD-RAM Speed", dvdRAMSpeed));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("CD-R/W Speed", cdRwSpeed));
        attributeList.add(new Attribute("Interface", _interface));
        attributeList.add(new Attribute("DVD-ROM Speed", dvdROMSpeed));
        attributeList.add(new Attribute("BD-RE Dual Layer Speed", bdREDualLayerSpeed));
        attributeList.add(new Attribute("DVD-RW Speed", dvdRWSpeed));
        attributeList.add(new Attribute("Form Factor", formFactor));
        attributeList.add(new Attribute("DVD-R Plus Speed", dvdRPlusSpeed));
        attributeList.add(new Attribute("DVD-R Plus Dual Layer Speed", dvdRPlusDualLayerSpeed));
        attributeList.add(new Attribute("CD-ROM Speed", cdROMSpeed));
        attributeList.add(new Attribute("DVD-R Plus RW Speed", dvdRPlusRWSpeed));
        attributeList.add(new Attribute("BD-ROM Speed", bdROMSpeed));
        attributeList.add(new Attribute("CD-R Speed", cdRSpeed));
        attributeList.add(new Attribute("DVD-R Dual Layer Speed", dvdRDualLayerSpeed));
        return attributeList;
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
        return ComponentCategory.OPTICAL_DRIVE.value;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getTitle() {
        return name;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String isCompatible(ComponentCategory category, List<Attribute> allParameters) {

        switch (category) {
            case MOTHERBOARD:
                return ProtocolConfiguration.CHECK_MOTHERBOARD_SATA;
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
        return "optical_drive";
    }

}
