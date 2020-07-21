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
    Integer pid;
    Integer sid;
    String pname;
    String price;
    String number;
    String description;

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid='" + pid + '\'' +
                ", sid='" + sid + '\'' +
                ", pname='" + pname + '\'' +
                ", price='" + price + '\'' +
                ", number='" + number + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
