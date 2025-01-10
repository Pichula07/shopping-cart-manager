package exception;

public class ProductUnavailableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ProductUnavailableException(String msg) {
        super(msg);
    }
}