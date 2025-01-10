package exception;

public class invalidProductException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public invalidProductException (String msg) {
		super(msg);
	}

}
