package scu.suncaper.mallback.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "order")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "oid")
    Integer oid;
    Integer uid;
    Integer pid;
    Date trade_time;
    Integer trade_num;
    String address;
    Boolean pay_or_not;
    Boolean deliver_or_not;

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

    public Boolean getPay_or_not() {
        return pay_or_not;
    }

    public void setPay_or_not(Boolean pay_or_not) {
        this.pay_or_not = pay_or_not;
    }

    public Boolean getDeliver_or_not() {
        return deliver_or_not;
    }

    public void setDeliver_or_not(Boolean deliver_or_not) {
        this.deliver_or_not = deliver_or_not;
    }
}
