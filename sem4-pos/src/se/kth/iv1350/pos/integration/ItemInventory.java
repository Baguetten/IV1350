package se.kth.iv1350.pos.integration;
import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents an inventory of items that can get added to a sale. 
 */
public class ItemInventory {
	private List<ItemDTO> itemInventory = new ArrayList<>();
	
	/**
	 * Creates the inventory
	 */
	public ItemInventory() {
		itemInventory.add(new ItemDTO(1001, "Vanilla Ice Cream", 23, 0.25, 0, 0));
		itemInventory.add(new ItemDTO(1002, "Chocolate Ice Cream", 30, 0.22, 0, 0));
		itemInventory.add(new ItemDTO(1003, "Caramel Ice Cream", 30, 0.30, 0, 0));
		itemInventory.add(new ItemDTO(1004, "Strawberry Ice Cream", 20, 0.25, 0, 0));
		itemInventory.add(new ItemDTO(1005, "Licorice Ice Cream", 25, 0.20, 0, 0));
	}
	
	/**
	 * Searches through the item inventory for a specific item. 
	 * 
	 * @param itemIdentifier The identifier of an item
	 * @return The specified item depending on the identifier
	 * @throws ItemIdentifierIsNotInInventoryException If the itemIdentifier didn't match any item in the itemInventory.
	 * @throws DatabaseConnectionException If there occurred an error connection with the database.
	 */
	public ItemDTO ItemInformation(int itemIdentifier) throws ItemIdentifierIsNotInInventoryException, DatabaseConnectionException {
		if (itemIdentifier == 1000) {
			throw new DatabaseConnectionException();
			}
		else {
			for (ItemDTO itemToCompareWith : itemInventory) {
				if (itemToCompareWith.getItemIdentifier() == itemIdentifier) return itemToCompareWith;
				}
			throw new ItemIdentifierIsNotInInventoryException(itemIdentifier);
			}
		}
	}
