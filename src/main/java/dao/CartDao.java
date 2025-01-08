package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import entity.CartItem;

public class CartDao {

    private final EntityManager entityManager;

    public CartDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<CartItem> getAllCartItems() {
        return entityManager.createQuery("FROM CartItem", CartItem.class).getResultList();
    }

    public void saveCartItem(CartItem cartItem) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(cartItem); // JPA merge para persistir ou atualizar a entidade
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }

    public void deleteCartItem(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            CartItem item = entityManager.find(CartItem.class, id);
            if (item != null) {
                entityManager.remove(item); // JPA remove
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        }
    }
}
