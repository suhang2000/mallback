package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "saler")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Saler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid")
    int sid;
    String sname;
    String password;
    String phone;
    String email;
    String address;
    String bank_num;
    String icon;
    Date register_time;

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBank_num() {
        return bank_num;
    }

    public void setBank_num(String bank_num) {
        this.bank_num = bank_num;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(Date register_time) {
        this.register_time = register_time;
    }

    @Override
    public String toString() {
        return "Saler{" +
                "sid=" + sid +
                ", sname='" + sname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", bank_num='" + bank_num + '\'' +
                ", icon='" + icon + '\'' +
                ", register_time=" + register_time +
                '}';
    }
}
