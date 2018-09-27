package grading;

public class SizeException extends Exception {
	
	//necessary value to conform to Exception type
	private static final long serialVersionUID = 1L;

	//constructor with no message
	public SizeException() {}
	
	//constructor that accepts a message
	public SizeException(String message) {
		super(message);
	}
}
