package controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import dao.CartDao;
import dao.ProductDao;
import entity.CartItem;
import entity.Product;
import exception.ProductUnavailableException;
import exception.ProductWithoutStockException;

class CartControllerTest {

    private CartDao cartDao;
    private ProductDao productDao;
    private CartController cartController;

    @BeforeEach
    void setUp() {
        cartDao = Mockito.mock(CartDao.class);
        productDao = Mockito.mock(ProductDao.class);
        cartController = new CartController(cartDao, productDao);
    }

    @Test
    void addProductToCart_ProductNotFound_ThrowsException() {
        when(productDao.getProductById(1L)).thenReturn(null);

        Exception exception = assertThrows(ProductUnavailableException.class, () -> {
            cartController.addProductToCart(1L, 1);
        });

        assertEquals("Product unavailable!", exception.getMessage());
    }

    @Test
    void addProductToCart_ProductWithoutStock_ThrowsException() {
        Product product = new Product(1L, "Test Product", 10.0, 0);
        when(productDao.getProductById(1L)).thenReturn(product);

        Exception exception = assertThrows(ProductWithoutStockException.class, () -> {
            cartController.addProductToCart(1L, 1);
        });

        assertEquals("Product without stock!", exception.getMessage());
    }

    @Test
    void addProductToCart_SuccessfulAddition_ReturnsUpdatedCart() {
        Product product = new Product(1L, "Test Product", 10.0, 10);
        when(productDao.getProductById(1L)).thenReturn(product);

        CartItem cartItem = new CartItem(null, 10.0, 2, 20.0, product);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);

        when(cartDao.getAllCartItems()).thenReturn(cartItems);

        List<CartItem> result = cartController.addProductToCart(1L, 2);

        // Verifica se o estoque foi atualizado
        assertEquals(8, product.getQuantity());

        // Verifica se o item foi adicionado ao carrinho
        assertEquals(1, result.size());
        assertEquals(cartItem.getProduct().getId(), result.get(0).getProduct().getId());
        assertEquals(20.0, result.get(0).getTotalValue());

        // Verifica se os métodos corretos foram chamados
        verify(productDao, times(1)).saveProduct(product);
        verify(cartDao, times(1)).saveCartItem(any(CartItem.class));
    }
}
