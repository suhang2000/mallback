package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "admin")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private int aid;

    /**
     * Password.
     */
    private String password;

    /**
     * Real name.
     */
    private String aname;

    /**
     * Phone number.
     */
    private String phone;

    public void setAid(int aid) {
        this.aid = aid;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAid() {
        return aid;
    }

    public String getPassword() {
        return password;
    }

    public String getAname() {
        return aname;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aid=" + aid +
                ", password='" + password + '\'' +
                ", aname='" + aname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
