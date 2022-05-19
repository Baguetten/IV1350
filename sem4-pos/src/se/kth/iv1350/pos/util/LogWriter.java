package se.kth.iv1350.pos.util;

/**
 * In charge of writing the error message to the logs for the developers.
 */
public class LogWriter {
	
	/**
	 * Logs the error message.
	 * 
	 * @param e The error exception used for logging the error message.
	 */
	public void logError(Exception e) {
		System.out.println("** Developer: " + e + " **");
	}
}
