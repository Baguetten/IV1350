package se.kth.iv1350.pos.controller;

/**
 * Throws when the specified item identifier does not exist in the item inventory catalogue.
 */
public class InvalidItemIdentifierException extends Exception {
	
	/**
	 * Creates a new instance stating the entered item identifier is invalid 
	 * and states that the item should be entered manually.
	 * 
	 */
	public InvalidItemIdentifierException() {
		super("Invalid item identifier, enter it manually.");
	}
}
