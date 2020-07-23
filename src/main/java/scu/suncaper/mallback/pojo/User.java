package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    Integer uid;
    String uname;
    String password;
    String phone;
    String email;
    String address;
    String gender;
    Date birthday;
    String icon;
    Date register_time;
    //@ManyToMany(mappedBy = "users")
//    public Set<Product> products = new HashSet<>();
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

//    public void setUid(Integer uid) {
//        this.uid = uid;
//    }
//
//    public Set<Product> getProducts() {
//        return products;
//    }

//    public void setProducts(Set<Product> products) {
//        this.products = products;
//    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", icon='" + icon + '\'' +
                ", register_time=" + register_time +
                '}';
    }
}
