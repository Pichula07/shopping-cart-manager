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

	public CartItem getCartItemByProductId(Long productId) {
		List<CartItem> cartItems = entityManager
				.createQuery("FROM CartItem c WHERE c.product.id = :productId", CartItem.class)
				.setParameter("productId", productId).getResultList();

		return cartItems.isEmpty() ? null : cartItems.get(0);
	}

	public CartItem getCartItemById(Long id) {
		return entityManager.find(CartItem.class, id);
	}

	public void saveCartItem(CartItem cartItem) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			if (cartItem.getId() == null) {
				entityManager.persist(cartItem);
			} else {
				entityManager.merge(cartItem);
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			throw e;
		}
	}

	public void deleteCartItem(Long id) {
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			transaction.begin();
			CartItem item = entityManager.find(CartItem.class, id);
			if (item == null) {
				throw new IllegalArgumentException("CartItem with ID " + id + " not found!");
			}
			entityManager.remove(item);
			transaction.commit();
		} catch (Exception e) {
			if (transaction.isActive())
				transaction.rollback();
			throw e;
		}
	}
}
