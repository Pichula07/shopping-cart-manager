package controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import dao.CartDao;
import dao.ProductDao;
import entity.CartItem;
import entity.Product;
import exception.ProductUnavailableException;
import exception.ProductWithoutStockException;

public class CartController {

	private final CartDao cartDao;
	private final ProductDao productDao;

	public CartController(CartDao cartDao, ProductDao productDao) {
		this.cartDao = cartDao;
		this.productDao = productDao;
	}

	public List<CartItem> addProductToCart(Long productId, int quantity) {
		
	    Product product = productDao.getProductById(productId);
	    if (product == null) {
	        throw new ProductUnavailableException("Product unavailable!");
	    }
	    if (product.getQuantity() < quantity) {
	        throw new ProductWithoutStockException("Product without stock!");
	    }
	    
	    product.setQuantity(product.getQuantity() - quantity);
	    productDao.saveProduct(product);

	    CartItem existingCartItem = cartDao.getCartItemByProductId(productId);

	    if (existingCartItem != null) {
	        existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
	        existingCartItem.setTotalValue(existingCartItem.getPrice() * existingCartItem.getQuantity());
	        cartDao.saveCartItem(existingCartItem);
	    } else {
	        CartItem newItem = new CartItem();
	        newItem.setProduct(product);
	        newItem.setQuantity(quantity);
	        newItem.setPrice(product.getPrice());
	        newItem.setTotalValue(product.getPrice() * quantity);
	        cartDao.saveCartItem(newItem);
	    }
	    return cartDao.getAllCartItems();
	}


	public void removeProductFromCart(Long productId, int quantity) {
	    CartItem cartItem = cartDao.getCartItemByProductId(productId);

	    if (cartItem == null) {
	        throw new ProductUnavailableException("Cart item not found!");
	    }
	    if (quantity <= 0 || quantity > cartItem.getQuantity()) {
	        throw new IllegalArgumentException("Invalid quantity to remove!");
	    }

	    Product product = cartItem.getProduct();
	    product.setQuantity(product.getQuantity() + quantity);
	    productDao.saveProduct(product);

	    if (cartItem.getQuantity() == quantity) {
	        cartDao.deleteCartItem(cartItem.getId());
	    } else {
	        cartItem.setQuantity(cartItem.getQuantity() - quantity);
	        cartItem.setTotalValue(cartItem.getPrice() * cartItem.getQuantity());
	        cartDao.saveCartItem(cartItem);
	    }
	}


	public List<CartItem> getCartItems() {
		return cartDao.getAllCartItems();
	}

	public double calculateTotal() {
	    List<CartItem> cartItems = cartDao.getAllCartItems();

	    double total = cartItems.stream()
	            .mapToDouble(CartItem::getTotalValue)
	            .sum();

	    return BigDecimal.valueOf(total).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}
