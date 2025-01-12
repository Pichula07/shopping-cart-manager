package view;

import java.util.List;
import java.util.Scanner;

import controller.CartController;
import controller.ProductController;
import entity.CartItem;
import entity.Product;
import exception.ProductUnavailableException;
import exception.ProductWithoutStockException;

public class MenuView {

	private final ProductController productController;
	private final CartController cartController;
	private final Scanner sc;

	public MenuView(ProductController productController, CartController cartController) {
		this.productController = productController;
		this.cartController = cartController;
		this.sc = new Scanner(System.in);
	}

	public void displayMenu() {
		while (true) {
			System.out.println("=== Menu ===");
			System.out.println("1. Show all products available");
			System.out.println("2. Add Product");
			System.out.println("3. Add Product to Cart");
			System.out.println("4. View Cart");
			System.out.println("5. Remove Products from Cart");
			System.out.println("6. Calculate Cart Total");
			System.out.println("7. Tax ");
			System.out.println("0. Exit");
			System.out.print("Choose an option: ");

			int choice;
			try {
				choice = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Invalid option. Please try again.");
				continue;
			}

			switch (choice) {
			case 1:
				showAllProducts();
				break;
			case 2:
				addProduct();
				break;
			case 3:
				addProductToCart();
				break;
			case 4:
				displayCart();
				break;
			case 5:
				removeProductFromCart();
				break;
			case 6:
				calculateTotal();
				break;
			case 7:
				deliveryTax();
				break;
			case 0:
				System.out.println("Exiting...");
				return;
			default:
				System.out.println("Invalid option. Please try again.");
			}
		}
	}

	
	private void showAllProducts() {
	    List<Product> products = productController.getAllProducts();
	    if (products.isEmpty()) {
	        System.out.println("No products available.");
	    } else {
	        System.out.println("Available products:");
	        for (Product product : products) {
	            System.out.printf("ID: %d | Name: %s | Price: $%.2f | Stock: %d%n", 
	                product.getId(), product.getName(), product.getPrice(), product.getQuantity());
	        }
	    }
	}

	
	
	
	
	
	private void addProduct() {
		try {
			System.out.print("Enter the product name: ");
			String name = sc.nextLine();

			System.out.print("Enter the product price: ");
			double price = Double.parseDouble(sc.nextLine());

			if (price < 0) {
				System.out.println("Error: Price cannot be negative.");
				return;
			}
			System.out.print("Enter the product quantity: ");
			int quantity = Integer.parseInt(sc.nextLine());

			if (quantity < 0) {
				System.out.println("Error: Quantity cannot be negative.");
				return;
			}

			Product product = new Product(null, name, price, quantity);
			productController.saveProduct(product);
			System.out.println("Product added successfully: " + name);
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid input. Please enter valid numbers for price and quantity.");
		} catch (ProductWithoutStockException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void addProductToCart() {
		try {
			System.out.print("Enter product ID: ");
			Long productId = Long.parseLong(sc.nextLine());

			System.out.print("Enter quantity: ");
			int quantity = Integer.parseInt(sc.nextLine());

			cartController.addProductToCart(productId, quantity);
			System.out.println("Product successfully added to cart.");
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid input. Please enter valid numbers.");
		} catch (ProductUnavailableException | ProductWithoutStockException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void displayCart() {
		List<CartItem> cartItems = cartController.getCartItems();
		if (cartItems.isEmpty()) {
			System.out.println("The cart is empty.");
		} else {
			System.out.println("Items in the cart:");
			for (CartItem item : cartItems) {
				System.out.printf("ID: %d | Product: %s | Quantity: %d | Price: $ %.2f | Total: $ %.2f%n", item.getId(),
						item.getProduct().getName(), item.getQuantity(), item.getPrice(), item.getTotalValue());
			}
			double totalCartValue = cartItems.stream().mapToDouble(CartItem::getTotalValue).sum();
			System.out.println("Total cart value: $" + String.format("%.2f", totalCartValue));
		}
	}

	private void removeProductFromCart() {
		try {
			System.out.print("Enter product ID: ");
			Long productId = Long.parseLong(sc.nextLine());

			System.out.print("Enter quantity to be removed: ");
			int quantity = Integer.parseInt(sc.nextLine());

			cartController.removeProductFromCart(productId, quantity);
			System.out.println("Product successfully removed from cart.");
		} catch (NumberFormatException e) {
			System.out.println("Error: Invalid input. Please enter valid numbers.");
		} catch (ProductUnavailableException | IllegalArgumentException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

	private void deliveryTax() {
		try {
			List<CartItem> cartItems = cartController.getCartItems();
			double totalCartValue = cartItems.stream().mapToDouble(CartItem::getTotalValue).sum();

			double deliveryTax = 0.0;
			if (totalCartValue > 100.0) {
				System.out.print("Enter the delivery tax: ");
				String taxInput = sc.nextLine();

				try {
					deliveryTax = Double.parseDouble(taxInput);
					System.out.println("Delivery tax of $" + deliveryTax + " applied.");
				} catch (NumberFormatException e) {
					System.out.println("Invalid tax input. Please enter a valid number.");
					return;
				}
			} else {
				System.out.println("The total cart value is below the delivery threshold. No delivery tax applied.");
			}

			double totalWithTax = totalCartValue + deliveryTax;

			System.out.println("Final purchase total (cart + tax): $" + String.format("%.2f", totalWithTax));

		} catch (Exception e) {
			System.out.println("Error: Invalid input. Please try again.");
		}
	}

	private void calculateTotal() {
		double total = cartController.calculateTotal();
		System.out.println("Total cart value: $ " + String.format("%.2f", total));
	}

}
