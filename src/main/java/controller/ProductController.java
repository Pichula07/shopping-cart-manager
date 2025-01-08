package controller;

import java.util.List;

import dao.ProductDao;
import entity.Product;

public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }

    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
