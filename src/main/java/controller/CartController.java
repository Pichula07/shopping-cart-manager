package controller;

import java.util.List;

import dao.CartDao;
import dao.ProductDao;
import entity.CartItem;
import entity.Product;

public class CartController {

    private final CartDao cartDao;
    private final ProductDao productDao;

    public CartController(CartDao cartDao, ProductDao productDao) {
        this.cartDao = cartDao;
        this.productDao = productDao;
    }
}
