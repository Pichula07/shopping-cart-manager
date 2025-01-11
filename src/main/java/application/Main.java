package application;

import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.CartController;
import controller.ProductController;
import dao.CartDao;
import dao.ProductDao;
import view.MenuView;

public class Main {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shopping_cart");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		ProductDao productDao = new ProductDao(entityManager);
		CartDao cartDao = new CartDao(entityManager);
		ProductController productController = new ProductController(productDao);
		CartController cartController = new CartController(cartDao, productDao);
		MenuView menuView = new MenuView(productController, cartController);

        menuView.displayMenu();
	}

}
