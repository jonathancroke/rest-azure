package exception;

public class RestAzureException extends Exception {
	private static final long serialVersionUID = 2185937598533942899L;

	public RestAzureException() {
	}

	public RestAzureException(String message) {
		super(message);
	}

	public RestAzureException(Throwable cause) {
		super(cause);
	}

	public RestAzureException(String message, Throwable cause) {
		super(message, cause);
	}
}
