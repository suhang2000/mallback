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

    public boolean boo(Integer cpid, Integer cuid){
        if(cartDAO.getAllByCuidAndCpid(cuid,cpid)!=null)
            return true;
        else
            return false;
    }
    @Transactional
    public void insertCart(Integer cpid,Integer cuid,Integer number){
        cartDAO.insertCart(cpid,cuid ,number);
    }

    @Transactional
    public void updateCart(Integer cpid,Integer cuid,Integer number){
        cartDAO.updateCart(cpid,cuid ,number);
    }



    public List<Cart> get() {
        return cartDAO.getAllByCuid(1);
    }

    public Cart getCertain(Integer  cid) {
        return cartDAO.getByCid(cid);
    }

    @Transactional
    public void addGoodsById(Integer cartToAdd){
        //cartDAO.insertByCid(cartToAdd);
    }



}
