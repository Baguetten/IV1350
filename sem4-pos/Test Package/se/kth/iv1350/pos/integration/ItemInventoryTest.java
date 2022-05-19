package se.kth.iv1350.pos.integration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemInventoryTest {
	private ItemInventory itemInventory;
	private ItemDTO itemFromItemInventory;
	private int itemIdentifier;
	
	@BeforeEach
	void setUp() throws Exception {
		itemInventory = new ItemInventory();
	}
	
	@AfterEach
	void tearDown() throws Exception {
		itemInventory = null;

	}
	@Test
	void testItemIdentifierIsNotInInventoryException() throws DatabaseConnectionException {
		itemIdentifier = 999;
		try {
			itemFromItemInventory = itemInventory.ItemInformation(itemIdentifier);
			assert(false);
		}
		catch (ItemIdentifierIsNotInInventoryException e) {
			assert(true);
		}
	}
	
	@Test 
	void testDatabaseConnectionException() throws ItemIdentifierIsNotInInventoryException {
		itemIdentifier = 1000;
		try {
			itemFromItemInventory = itemInventory.ItemInformation(itemIdentifier);
			assert(false);
		}
		catch (DatabaseConnectionException e){
			assert(true);
		}
	}
	
	
	@Test
	void testValidItemIdentifier() throws DatabaseConnectionException {
		itemIdentifier = 1001;
		try {
			itemFromItemInventory = itemInventory.ItemInformation(itemIdentifier);
			}
		catch (ItemIdentifierIsNotInInventoryException e){
			assert(false);
		}
	}
}
