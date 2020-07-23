package scu.suncaper.mallback.service;

import antlr.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.suncaper.mallback.dao.ProductDAO;
import scu.suncaper.mallback.pojo.Product;
import scu.suncaper.mallback.result.Result;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;

    public List<Product> get(String pname) {
        if ("".equals(pname)) {
            return productDAO.findAll();
        }
        else{
            return productDAO.getAllByPname(pname);
        }
    }
    public Product  getCertain(Integer  pid) {
        return productDAO.getByPid(pid);
    }

    @Transactional
    public void dropGoodsById(Integer productToDelete){
        productDAO.deleteByPid(productToDelete);
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }
}
