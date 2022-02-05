package it.siliconsquare.model.administration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @Column(name = "id_role", nullable = false)
    private int idRole;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "color")
    private String color;

    public String getColor() {
        return color;
    }

    public Role() {
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "[" +
                "idRole:\"" + idRole +
                "\", roleName:\"" + roleName + '\"' +
                ", color:\"" + color + '\"' +
                ']';
    }
}
