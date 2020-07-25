package scu.suncaper.mallback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scu.suncaper.mallback.dao.ProductDAO;
import scu.suncaper.mallback.pojo.Product;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductDAO productDAO;
    //管理人员用，来查询全部订单
    public List<Object[]> get(String pname) {
        if ("".equals(pname)) {
            System.out.println("开始查询全部内容");
            return productDAO.findAllProducts();
        }
        else{
            System.out.println("开始查询指定内容");
            return productDAO.findAllProductsByPname(pname);
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
    }

    public List<Product> getProducts() {
        return productDAO.findAll();
    }

    public List<Product> getProductsByPnameAndSname(String pname, String sname){
        if("".equals(pname)) {
            return productDAO.findBySname(sname);
        }
        else{
            return productDAO.findByPnameAndSname(pname,sname);
        }
    }
    public void save(Product product) {
        productDAO.save(product);
    }

    public List<Product> search(String keywords) {
        return productDAO.findAllByPnameLikeOrDescriptionLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
