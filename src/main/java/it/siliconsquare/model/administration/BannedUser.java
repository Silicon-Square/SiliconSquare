package it.siliconsquare.model.administration;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "banned_user")
public class BannedUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_ban", nullable = false)
    private int idBan;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    @Column(name = "ban_date")
    private Date banDate;

    @Column(name = "duration")
    private int duration;

    public BannedUser() {
    }

    public int getIdBan() {
        return idBan;
    }

    public void setIdBan(int idBan) {
        this.idBan = idBan;
    }

    public User getUser() {
        return user;
    }

    public int getIdUser() {
        return user.getIdUser();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBanDate() {
        return banDate;
    }

    public void setBanDate(Date banDate) {
        this.banDate = banDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "BannedUser{" +
                "idBan=" + idBan +
                ", idUser=" + user.getIdUser() +
                ", banDate=" + banDate +
                ", duration=" + duration +
                '}';
    }
}
