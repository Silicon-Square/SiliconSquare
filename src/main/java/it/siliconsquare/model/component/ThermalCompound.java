package it.siliconsquare.model.component;

import com.google.gson.annotations.SerializedName;
import it.siliconsquare.common.ProtocolConfiguration;
import it.siliconsquare.common.redirect.ComponentCategory;
import it.siliconsquare.model.administration.State;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "thermal_compound")
public class ThermalCompound extends Component {

    @Id
    @Column(name = "id_thermal_compound", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @Column(name = "image")
    private String image = "https://resources.siliconsquare.it/assets/images/component/thermal_compound.webp";

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

    @SerializedName("Part Number")
    @Column(name = "part_n")
    private String partN;

    @SerializedName("Amount")
    @Column(name = "amount")
    private String amount;

    @SerializedName("Features")
    @Column(name = "features")
    private String features;

    @Column(name = "is_visible")
    private boolean isVisible = true;

    @JoinColumn(name = "thermal_compound_state")
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

    public String getPartN() {
        return partN;
    }

    public void setPartN(String partN) {
        this.partN = partN;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ThermalCompound{" + "idThermalCoumpound=" + id + ", partN='" + partN + '\'' + ", amount='" + amount
                + '\'' + ", amazonLinkUrl=" + amazonLinkUrl + ", manufacturer='" + manufacturer + '\'' + ", model='"
                + model + '\'' + ", features='" + features + '\'' + ", image=" + image + ", name='" + name + '\'' + '}';
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
        return ComponentCategory.THERMAL_COMPOUND.value;
    }

    @Override
    public List<Attribute> getAllSpecifications() {
        List<Attribute> attributeList = new ArrayList<>();
        attributeList.add(new Attribute("Part N", partN));
        attributeList.add(new Attribute("Amount", amount));
        attributeList.add(new Attribute("Manufacturer", manufacturer));
        attributeList.add(new Attribute("Model", model));
        attributeList.add(new Attribute("Features", features));
        return (attributeList);

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
        return ProtocolConfiguration.COMPATIBLE;
    }

    @Override
    public List<Attribute> getAllCompatibilityParameters() {
        List<Attribute> attributeList = new ArrayList<>();
        return attributeList;
    }

    @Override
    public String getObjectTableName() {
        return "thermal_compound";
    }
}
