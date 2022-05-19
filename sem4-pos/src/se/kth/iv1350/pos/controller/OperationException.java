package se.kth.iv1350.pos.controller;

/**
 * Throws if there is a connection issue with the database server.
 */
public class OperationException extends Exception {
	
	/**
	 * Creates a new instance stating there was a connection error with the database server.
	 */
	public OperationException() {
		super("Issues with the database, contact a staff.");
	}
}
