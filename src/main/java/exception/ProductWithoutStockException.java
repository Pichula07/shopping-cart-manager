package exception;

public class ProductWithoutStockException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	

	public ProductWithoutStockException(String message) {
        super(message);
    }
}