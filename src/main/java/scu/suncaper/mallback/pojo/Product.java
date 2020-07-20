package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pid")
    String pid;
    String sid;
    String pname;
    String price;
    String number;
    String decription;

    public String getPid() {
        return pid;
    }

    public void setPid(int uid) {
        this.pid = pid;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String uname) {
        this.sid = sid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String password) {
        this.pname = pname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String phone) {
        this.price = price;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String email) {
        this.number = number;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String address) {
        this.decription = decription;
    }


    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", sid='" + sid + '\'' +
                ", pname='" + pname + '\'' +
                ", price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", decription='" + decription + '\'' +
                '}';
    }
}
