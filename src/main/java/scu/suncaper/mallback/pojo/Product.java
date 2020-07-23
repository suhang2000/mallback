package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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
    Double price;
    Integer number;
    String description;
    @ManyToMany(targetEntity = User.class, cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinTable(name = "order",
            joinColumns = {@JoinColumn(name = "pid",referencedColumnName = "pid")},
            inverseJoinColumns = {@JoinColumn(name = "uid",referencedColumnName = "uid")}
    )
    public Set<User> users = new HashSet<>();
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
    public void setPname(String pname) {
        this.pname = pname;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
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
