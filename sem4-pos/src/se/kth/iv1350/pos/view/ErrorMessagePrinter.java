package se.kth.iv1350.pos.view;

/**
 * In charge of printing the error message to the view for the users.
 */
public class ErrorMessagePrinter {
	
	/**
	 * Prints the error message.
	 * 
	 * @param e The error exception used for printing the error message.
	 */
	void printErrorMessage(Exception e) {
		System.out.println("** User: " + e.getMessage() + " **");
	}
}
