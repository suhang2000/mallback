package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.suncaper.mallback.dao.CartDAO;
import scu.suncaper.mallback.pojo.Cart;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartDAO cartDAO;

    public boolean boo(Integer cpid, String name){
        return cartDAO.booCart(cpid,name)!=null;
    }

    @Transactional
    public void insertCart(Integer cpid,String name){
        cartDAO.insertCart(cpid,name);
    }

    @Transactional
    public void updateCart(Integer cpid,String name){
        cartDAO.updateCart(cpid,name);
    }

    public List<List> getCart(String uname){
        return cartDAO.getCart(uname);
    }

    public void removeGoods(Integer cid){
        cartDAO.removeGoods(cid);
    }

    public int cartProNum(int cid){
        return cartDAO.cartProNumber(cid);
    }

    public void addGoods(Integer cid){
        cartDAO.addGoods(cid);
    }

    public Integer proNum(int cid){
        return cartDAO.proNumber(cid);
    }

    public List<Cart> get() {
        return cartDAO.getAllByCuid(1);
    }

    public Cart getCertain(Integer  cid) {
        return cartDAO.getByCid(cid);
    }

    @Transactional
    public void dropGoodsById(Integer cartToDelete){
        cartDAO.deleteByCid(cartToDelete);
    }



}
