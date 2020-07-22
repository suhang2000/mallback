package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "admin")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private int aid;

    /**
     * Real name.
     */
    @Size(max=50,min=2)
    private String aname;

    private String salt;

    /**
     * Password.
     */
    private String password;

    /**
     * Phone number.
     */
    @Pattern(regexp = "1[3|4|5|7|8][0-9]\\d{8}")
    private String phone;

    public void setAid(int aid) { this.aid = aid; }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public void setSalt(String salt) { this.salt = salt; }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAid() {
        return aid;
    }

    public String getAname() {
        return aname;
    }

    public String getSalt() { return salt; }

    public String getPassword() {
        return password;
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
