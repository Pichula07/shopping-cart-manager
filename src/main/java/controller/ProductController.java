package controller;

import java.util.List;

import dao.ProductDao;
import entity.Product;
import exception.ProductUnavailableException;
import exception.ProductWithoutStockException;
import exception.invalidProductException;

public class ProductController {

    private final ProductDao productDao;

    public ProductController(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> getAllProducts() {
        return productDao.getAllProducts();
    }
    
    public Product getProductById(Long id) {
    	if(id ==null || id <0){
    		 throw new invalidProductException("Invalid product ID");
    	}
    	 Product product = productDao.getProductById(id);
    	 
    	if(product ==null) {
    		 throw new ProductUnavailableException("Product not found for ID: " + id);
    	}
    	
    	return product;
    }
    
     public void saveProduct(Product product) {
        if (product == null) {
            throw new ProductWithoutStockException("Product cannot be null");
        }
        productDao.saveProduct(product); 
    }
}
