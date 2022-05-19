package se.kth.iv1350.pos.integration;

/**
 * Throws when the specified item identifier does not exist in the item inventory catalogue.
 */
public class ItemIdentifierIsNotInInventoryException extends Exception {
	
	/**
	 * Creates a new instance stating the entered item identifier is invalid.
	 * 
	 * @param invalidItemIdentifier The invalid item identifier entered.
	 */
	public ItemIdentifierIsNotInInventoryException(int invalidItemIdentifier) {
		super("The entered itemIdentifier " + invalidItemIdentifier + " does not exist in the item inventory catalog.");
	}

}
