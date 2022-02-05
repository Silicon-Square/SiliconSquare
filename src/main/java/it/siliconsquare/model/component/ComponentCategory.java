package it.siliconsquare.model.component;

import javax.persistence.*;

@Entity
@Table(name = "component_category")
public class ComponentCategory {
    @Id
    @Column(name = "id_component_category", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idComponentCategory;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image")
    private String image;

    @Column(name = "path")
    private String path;

    @Column(name = "total")
    private int total;

    @Column(name = "total_published")
    private int totalPublished;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "single")
    private boolean single;

    public ComponentCategory() {

    }

    public ComponentCategory(int idComponentCategory, String name, String image, String description, String displayName) {
        this.idComponentCategory = idComponentCategory;
        this.name = name;
        this.image = image;
        this.description = description;
        this.displayName = displayName;
    }

    public String getImage() {
        return image;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getIdComponentCategory() {
        return idComponentCategory;
    }

    public void setIdComponentCategory(int idComponentCategory) {
        this.idComponentCategory = idComponentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPublished() {
        return totalPublished;
    }

    public void setTotalPublished(int totalPublished) {
        this.totalPublished = totalPublished;
    }

    public boolean isSingle() {
        return single;
    }

    public void setSingle(boolean single) {
        this.single = single;
    }
}
