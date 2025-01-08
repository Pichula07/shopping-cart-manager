package application;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import controller.CartController;
import controller.ProductController;
import dao.CartDao;
import dao.ProductDao;
import entity.Product;
import view.MenuView;

public class Main {

	public static void main(String[] args) {

		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("shopping_cart");
		EntityManager entityManager = entityManagerFactory.createEntityManager();

	
	}
}
