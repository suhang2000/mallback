package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order")
public class UserProduct {
    @Id
    @Column(name = "oid")
    Integer oid;
    Integer uid;
    Integer pid;
    String pname;
    Date trade_time;
    Integer trade_num;
    String address;
    Integer pay_or_not;
    Integer deliver_or_not;

    UserProduct(){
        super();
    }
    UserProduct(Integer oid){
        super();
        this.uid =oid;
    }
    public Integer getOid() {
        return oid;
    }
    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getUid() {
        return uid;
    }
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getPid() {
        return pid;
    }
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Date getTrade_time() {
        return trade_time;
    }
    public void setTrade_time(Date trade_time) {
        this.trade_time = trade_time;
    }

    public Integer getTrade_num() {
        return trade_num;
    }
    public void setTrade_num(Integer trade_num) {
        this.trade_num = trade_num;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPay_or_not() {
        return pay_or_not;
    }
    public void setPay_or_not(Integer pay_or_not) {
        this.pay_or_not = pay_or_not;
    }

    public Integer getDeliver_or_not() {
        return deliver_or_not;
    }
    public void setDeliver_or_not(Integer deliver_or_not) {
        this.deliver_or_not = deliver_or_not;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Order{" +
                "oid='" + oid + '\'' +
                ", uid='" + uid + '\'' +
                ", pid='" + pid + '\'' +
                ", trade_time='" + trade_time + '\'' +
                ", trade_num='" + trade_num + '\'' +
                ", address='" + address + '\'' +
                ", pay_or_not='" + pay_or_not + '\'' +
                ", deliver_or_not='" + deliver_or_not + '\'' +
                '}';
    }
}
