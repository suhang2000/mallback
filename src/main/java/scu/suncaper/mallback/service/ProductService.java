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

    @Transactional
    public void addProduct(Product newProduct){
            Product pro = new Product();
            pro.setPname(newProduct.getPname());
            pro.setNumber(newProduct.getNumber());
            pro.setDescription(newProduct.getDescription());
            pro.setPrice(newProduct.getPrice());
            pro.setSid(newProduct.getSid());

            Product res = productDAO.save(pro);
            System.out.println("after insert res: " + res);
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    public void save(Product product) {
        productDAO.save(product);
    }
}
