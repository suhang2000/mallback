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

    public List<Object[]> get() {
        return productDAO.findAllProducts();
    }

    public Product getCertain(Integer  pid) {
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

    public List<Product> getProductsBySname(String sname){
        System.out.println(productDAO.findBySname(sname));
        return productDAO.findBySname(sname);
    }
    public void save(Product product) {
        productDAO.save(product);
    }

    public List<Product> search(String keywords) {
        return productDAO.findAllByPnameLikeOrDescriptionLike('%' + keywords + '%', '%' + keywords + '%');
    }
}
