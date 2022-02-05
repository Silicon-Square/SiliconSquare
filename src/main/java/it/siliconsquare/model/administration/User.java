package it.siliconsquare.model.administration;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "public.user")
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idUser;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "date_birth")
    private Date dateBirth;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "registration_date")
    private Date registrationDate;

    @JoinColumn(name = "id_role")
    @ManyToOne
    private Role role;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "experience", nullable = false)
    private String experience;

    @Column(name = "banned", nullable = false)
    private boolean banned;

    public User() {
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getIdRole() {
        return role.getIdRole();
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role idRole) {
        this.role = idRole;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public int getExperience() {
        return Integer.parseInt(experience);
    }

    public void setExperience(int experience) {
        this.experience = String.valueOf(experience);
    }

    @Override
    public String toString() {
        return "User [avatar=" + avatar + ", banned=" + banned + ", dateBirth=" + dateBirth + ", email=" + email
                + ", experience=" + experience + ", idUser=" + idUser + ", name=" + name + ", registrationDate="
                + registrationDate + ", role=" + role + ", surname=" + surname + ", username=" + username + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return idUser == user.idUser && name.equals(user.name) && surname.equals(user.surname)
                && Objects.equals(dateBirth, user.dateBirth) && username.equals(user.username)
                && email.equals(user.email) && registrationDate.equals(user.registrationDate) && role.equals(user.role)
                && avatar.equals(user.avatar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, name, surname, dateBirth, username, email, registrationDate, role, avatar);
    }

}
