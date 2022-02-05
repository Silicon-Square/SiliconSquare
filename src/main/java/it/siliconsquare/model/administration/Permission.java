package it.siliconsquare.model.administration;

import javax.persistence.*;

@Entity
@Table(name = "permission")
public class Permission {

    @Id
    @Column(name = "id_permission", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPermission;

    @JoinColumn(name = "id_role")
    @ManyToOne
    private Role role;

    @JoinColumn(name = "id_action")
    @ManyToOne
    private Action action;

    @Column(name = "crud")
    private String crud; //Create Read Update Delete

    public Permission() {
    }

    public int getIdPermission() {
        return idPermission;
    }

    public void setIdPermission(int idPermission) {
        this.idPermission = idPermission;
    }

    public Role getRole() {
        return role;
    }

    public int getIdRole() {
        return role.getIdRole();
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Action getAction() {
        return action;
    }

    public int getIdAction() {
        return action.getIdAction();
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getCRUD() {
        return crud;
    }

    public void setCRUD(String crud) {
        this.crud = crud;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "idPermission=" + idPermission +
                ", idRole=" + role.getIdRole() +
                ", idAction=" + action.getIdAction() +
                ", crud='" + crud + '\'' +
                '}';
    }
}
