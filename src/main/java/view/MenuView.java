package view;

import controller.CartController;
import controller.ProductController;

public class MenuView {

	private final ProductController productController;
	private final CartController cartController;

	public MenuView(ProductController productController, CartController cartController) {
		this.productController = productController;
		this.cartController = cartController;
	}

	public void displayMenu() {}
       
}
