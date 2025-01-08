package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import entity.Product;

public class ProductDao {

    private final EntityManager entityManager;

    public ProductDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Product> getAllProducts() {
        return entityManager.createQuery("FROM Product", Product.class).getResultList();
    }

    public Product getProductById(Long id) {
        return entityManager.find(Product.class, id);
    }

    public void saveProduct(Product product) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(product); 
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }
}
