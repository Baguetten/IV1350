package se.kth.iv1350.pos.integration;

/**
 * Throws if there is a connection issue with the database server.
 */
public class DatabaseConnectionException extends Exception {
	
	/**
	 * Creates a new instance stating there was a connection error with the database server
	 * and states that a staff should be contacted.
	 */
	public DatabaseConnectionException() {
		super("Error SA3-DA13F_ADN13-KGB2 could not access the database server.");
	}
}
