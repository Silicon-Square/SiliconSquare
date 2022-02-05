package it.siliconsquare.model.administration;

import javax.persistence.*;

@Entity
@Table(name = "action")
public class Action {

    @Id
    @Column(name = "id_action", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idAction;

    @Column(name = "name")
    private String name;

    @Column(name = "path")
    private String path;

    public Action() {
    }

    public Action(int idAction, String name, String path) {
        this.idAction = idAction;
        this.name = name;
        this.path = path;
    }

    public int getIdAction() {
        return idAction;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setIdAction(int idAction) {
        this.idAction = idAction;
    }

    public void setName(String name) {
        this.name = name;
    }


}
