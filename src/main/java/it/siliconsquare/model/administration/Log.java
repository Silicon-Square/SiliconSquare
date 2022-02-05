package it.siliconsquare.model.administration;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "log")
public class Log {

    @Id
    @Column(name = "id_log", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idLog;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "action_perfomed")
    private Date actionPerformed;

    @JoinColumn(name = "id_action")
    @ManyToOne
    private Action action;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private User user;

    public Log() {
    }

    public int getIdLog() {
        return idLog;
    }

    public void setIdLog(int idLog) {
        this.idLog = idLog;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Date getActionPerformed() {
        return actionPerformed;
    }

    public void setActionPerformed(Date actionPerformed) {
        this.actionPerformed = actionPerformed;
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

    public User getUser() {
        return user;
    }

    public int getIdUser() {
        return user.getIdUser();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Log{" +
                "idLog=" + idLog +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", actionPerformed=" + actionPerformed +
                ", idAction=" + action.getIdAction() +
                ", idUser=" + user.getIdUser() +
                '}';
    }
}
